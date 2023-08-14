package com.xw.realm;

import com.xw.constant.TokenType;
import com.xw.dto.RolePermissionDTO;
import com.xw.dto.UserDTO;
import com.xw.dto.UserRoleDTO;
import com.xw.service.RolePermissionService;
import com.xw.service.UserRoleService;
import com.xw.service.UserService;
import com.xw.token.UsernamePasswordTypeToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SimpleRealm extends AuthorizingRealm {

    /*private static Map<String, String> userMap = new HashMap<>();

    static {
        userMap.put("xxx", "12345");
    }*/

    /*@Value("#{${user.map}}")
    private Map<String, String> userMap;

    @Value("#{${role.map}}")
    private Map<String, Set<String>> roleMap;*/

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public String getName() {
        return "simpleRealm";
    }

    /**
     * 获取认证信息
     * @param token the authentication token containing the user's principal and credentials.
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        UserDTO userDTO = userService.queryByUserName(userName);
        if (userDTO == null) {
            throw new UnknownAccountException("User does not exist.");
        }

        // 设置盐值
        ByteSource bytes = ByteSource.Util.bytes(userName);
        return new SimpleAuthenticationInfo(userName, userDTO.getPassword(), bytes, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<UserRoleDTO> roles = userRoleService.queryByUserName(userName);
        info.setRoles(roles.stream().map(UserRoleDTO::getRoleName).collect(Collectors.toSet()));

        Set<String> permissions = new HashSet();
        // 获取具有的所有角色的权限
        for (String role : info.getRoles()) {
            List<RolePermissionDTO> permissionsList = rolePermissionService.queryByRoleName(role);
            if (!CollectionUtils.isEmpty(permissionsList)) {
                permissions.addAll(new HashSet<>(permissionsList.stream().map(RolePermissionDTO::getPermission).collect(Collectors.toSet())));
            }
        }
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 哈希次数
        hashedCredentialsMatcher.setHashIterations(1024);
        // 编码方式
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        UsernamePasswordTypeToken typeToken = (UsernamePasswordTypeToken) token;
        return TokenType.NORMAL.getType().equals(typeToken.getType());
    }

}