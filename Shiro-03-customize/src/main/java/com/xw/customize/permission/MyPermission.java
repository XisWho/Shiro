package com.xw.customize.permission;

import lombok.Data;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;

import java.util.List;

/**
 * @author xx
 * 权限：
 * 0 表示 所有权限；1 新增（二进制：0001）、2 修改（二进制：0010）、4 删除（二进制：0100）、
 * 8 查看（二进制：1000）；
 * 如 +salary+10 表示对资源salary拥有修改/查看权限。(10=2+8，这里用十进制表示，但是计算的时候使用的是位运算符)
 */
@Data
public class MyPermission implements Permission {

    protected static final String WILDCARD_TOKEN = "*";

    protected static final String PART_DIVIDER_TOKEN = "\\+";

    // 用户标识
    private String resource;

    // 权限
    private int permissionBit;

    // 业务id
    // 比如：user:del:5，意思是用户标识为5的用户具有del权限
    private String bizId;

    public MyPermission(String permissionStr) {
        permissionStr = StringUtils.clean(permissionStr);
        List<String> parts = CollectionUtils.asList(permissionStr.split(PART_DIVIDER_TOKEN));
        // +salary+4+9，part.get(0)是空
        resource = parts.get(1);
        if (parts.size() == 2) {
            // 表示具有所有权限
            permissionBit = 0;
        } else if (parts.size() == 3) { // 没有写业务id
            permissionBit = Integer.parseInt(parts.get(2));
            bizId = WILDCARD_TOKEN;
        } else if (parts.size() == 4) { // 写了业务id
            permissionBit = Integer.parseInt(parts.get(2));
            bizId = parts.get(3);
        }
    }


    /**
     * 和权限字符串得出的Permission进行比较，判断是否授权
     */
    @Override
    public boolean implies(Permission p) {
        if (!(p instanceof MyPermission)) {
            return false;
        }
        MyPermission target = (MyPermission) p;
        if (!this.resource.equals(target.getResource())) {
            return false;
        }
        if (!(this.permissionBit == 0 || (this.permissionBit & target.permissionBit) != 0)) {
            return false;
        }
        if (!(WILDCARD_TOKEN.equals(this.getBizId()) || this.bizId.equals(target.getBizId()))) {
            return false;
        }
        System.out.println("MyPermission implies true");
        return true;
    }
}
