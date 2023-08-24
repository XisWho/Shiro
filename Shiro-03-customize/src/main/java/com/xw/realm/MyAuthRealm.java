package com.xw.realm;

import com.xw.customize.permission.MyPermissionResolver;
import com.xw.token.UsernamePasswordTypeToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyAuthRealm extends AuthorizingRealm {

    private static Map<String, String> userMap = new HashMap<>();

    private static Map<String, Set<String>> roleMap = new HashMap<>();

    private static Map<String, Set<String>> permissionMap = new HashMap<>();

//    user.map={"xx":"8541ce397d2a4e29749adf373d104fed", "yy":"9d5e518c301315900b27c566fa6bfd76"}
//    role.map={"xx":"admin,myRole", "yy":"super", "zz":"manager"}
//    permission.map={"admin":"user:add,+salary+4+9,run:add", "super":"user:add,user:del,run:del", "manager":"run:edit"}
    static {
        userMap.put("Jack", "12345");

        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roleMap.put("Jack", roles);

        Set<String> permissions = new HashSet<>();
        permissions.add("admin");
        permissions.add("+salary+4+7");
        permissionMap.put("admin", permissions);
    }

    public MyAuthRealm() {
        super();
        this.setPermissionResolver(new MyPermissionResolver());
    }

    @Override
    public String getName() {
        return "myAuthRealm";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        if (userMap.get(userName) == null) {
            throw new UnknownAccountException("user not exists");
        }
        return new SimpleAuthenticationInfo(userName, userMap.get(userName), null, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        Set<String> roles = roleMap.get(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permissions = new HashSet<>();
        for (String role : roles) {
            Set<String> set = permissionMap.get(role);
            if (!CollectionUtils.isEmpty(set)) {
                permissions.addAll(set);
            }
        }
        if (permissions.size() > 0) {
            info.setStringPermissions(permissions);
        }
        info.setRoles(roles);
        return info;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
//        UsernamePasswordTypeToken typeToken = (UsernamePasswordTypeToken) token;
//        return TokenType.ADMIN.getType().equals(typeToken.getType());
        return token instanceof UsernamePasswordTypeToken;
    }

}
