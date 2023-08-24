package com.xw.realm;

import com.xw.token.UsernamePasswordTypeToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyRealm extends AuthenticatingRealm {

    private static Map<String, String> userMap = new HashMap<>();

    static {
        userMap.put("xxx", "12345");
        userMap.put("Ben", "123456");
    }

    @Override
    public String getName() {
        return "myRealm";
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
    public boolean supports(AuthenticationToken token) {
//        UsernamePasswordTypeToken typeToken = (UsernamePasswordTypeToken) token;
//        return TokenType.ADMIN.getType().equals(typeToken.getType());
        return token instanceof UsernamePasswordTypeToken;
    }

}
