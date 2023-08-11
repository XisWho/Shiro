package com.xw.config;

import com.xw.realm.SimpleRealm;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleShiroConfig {

    @Bean
    public SessionsSecurityManager securityManager(@Autowired SimpleRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
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
        definition.addPathDefinition("/user/**", "roles[\"admin\"]");
        return definition;
    }

}
