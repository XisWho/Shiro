package com.xw.config;

import com.xw.customize.authenticator.MyAuthenticator;
import com.xw.realm.SimpleRealm;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SimpleShiroConfig {

    @Bean
    public SessionsSecurityManager securityManager(@Autowired List<Realm> realms, @Autowired MyAuthenticator myAuthenticator) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 这里注意一下，setAuthenticator需要比setRealms靠前，否则Authenticator中没有保存到Realm，导致doAuthenticate方法中的assertRealmsConfigured()失败
        securityManager.setAuthenticator(myAuthenticator);
        securityManager.setRealms(realms);

//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionListeners(sessionListeners);
//        sessionManager.setSessionDAO(redisSessionDAO);
//        sessionManager.setGlobalSessionTimeout(5000L);
//        sessionManager.setSessionValidationInterval(6000L);
//        securityManager.setSessionManager(sessionManager);

//        securityManager.setCacheManager(new RedisCacheManager());

        return securityManager;
    }

    /**
     * 设置需要拦截的url
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        // 进入manage页面需要认证
        definition.addPathDefinition("/manage/**", "authc");
        // 进入user页面需要admin角色
        // definition.addPathDefinition("/user/**", "roles[\"admin\"]");
        return definition;
    }

}
