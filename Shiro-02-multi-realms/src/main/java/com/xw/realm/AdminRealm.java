package com.xw.realm;

import com.xw.constant.TokenType;
import com.xw.token.UsernamePasswordTypeToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AdminRealm extends AuthorizingRealm {

    private static Map<String, String> userMap = new HashMap<>();

    static {
        userMap.put("admin", "12345");
    }

    @Override
    public String getName() {
        return "adminRealm";
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
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        UsernamePasswordTypeToken typeToken = (UsernamePasswordTypeToken) token;
        return TokenType.ADMIN.getType().equals(typeToken.getType());
    }

}
