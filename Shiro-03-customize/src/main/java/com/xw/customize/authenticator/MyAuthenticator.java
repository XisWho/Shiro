package com.xw.customize.authenticator;

import com.xw.customize.authenticationStrategy.MyAuthenticationStrategy;
import com.xw.token.UsernamePasswordTypeToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MyAuthenticator extends ModularRealmAuthenticator {

    @Value("#{${realm.map}}")
    private Map<String, String> realmTypeMap;

    public MyAuthenticator() {
        this.setAuthenticationStrategy(new MyAuthenticationStrategy());
    }

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();
        Collection<Realm> realms = getRealms();
        String type = ((UsernamePasswordTypeToken) authenticationToken).getType();
        // 这里实现的效果是根据传递的type参数区分不同等级的用户：NORMAL，VIP，然后再交由不同的AuthenticatingRealm进行认证。
        List<Realm> filterRealmes = realms.stream().filter(realm -> type.equals(realmTypeMap.get(realm.getName()))).collect(Collectors.toList());
        System.out.println(filterRealmes.stream().map(Realm::getName).reduce((name1, name2) -> name1+", "+name2).get() + " authenticate " + type);
        if (filterRealmes.size() == 1) {
            return doSingleRealmAuthentication(filterRealmes.iterator().next(), authenticationToken);
        } else {
            return doMultiRealmAuthentication(filterRealmes, authenticationToken);
        }
    }

}
