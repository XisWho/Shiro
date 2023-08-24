package com.xw.customize.credentialsMatcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCredentialsMatcher implements CredentialsMatcher {

    private Map<String, AtomicInteger> cacheMap = new HashMap<>();

    private static final int MAX_TIMES = 3;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String tokenCredential = new String((char[]) token.getCredentials());
        String infoCredentials = (String) info.getCredentials();
        // 反向密码
        String reverse = new StringBuilder(tokenCredential).reverse().toString();
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = cacheMap.get(username);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            cacheMap.put(username, retryCount);
        }
        // 如果密码输入MAX_TIMES次都错误，则锁定账号
        if (retryCount.get() >= MAX_TIMES) {
            System.out.println(username + " locked.");
            // 锁定账户
            throw new LockedAccountException("failed more then 3 times");
        }
        if (reverse.equals(infoCredentials)) {
            cacheMap.remove(username);
            return true;
        } else {
            retryCount.incrementAndGet();
            return false;
        }
    }

}
