package com.xw.controller;

import com.xw.token.UsernamePasswordTypeToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String userName, String password, String type, String rememberMe) {
        UsernamePasswordTypeToken token = new UsernamePasswordTypeToken(userName, password, type);
        if ("y".equals(rememberMe)) {
            token.setRememberMe(true);
        }
        try {
            SecurityUtils.getSubject().login(token);
        }   catch (LockedAccountException e) {
            return "locked";
        }
        catch (Exception e) {
            return "login";
        }
        return "user";
    }

    @GetMapping("/doLogout")
    public String doLogout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

    // 添加该接口
    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

}