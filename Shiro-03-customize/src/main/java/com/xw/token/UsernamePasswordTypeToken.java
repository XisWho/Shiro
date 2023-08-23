package com.xw.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author xx
 */
public class UsernamePasswordTypeToken extends UsernamePasswordToken {

    private String type;

    public UsernamePasswordTypeToken(final String username, final String password, final String type) {
        super(username, password);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
