package com.xw.customize.authenticationStrategy;

import org.apache.shiro.authc.AbstractAuthenticator;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyAuthenticationStrategy extends AbstractAuthenticationStrategy {

    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        if (singleRealmInfo!= null && aggregateInfo != null && !isEmpty(aggregateInfo.getPrincipals())) {
            System.out.println("more than one realm success");
            throw new AuthenticationException("more than one realm success");
        }
        return super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo, t);
    }

    private static boolean isEmpty(PrincipalCollection pc) {
        return pc == null || pc.isEmpty();
    }

}
