package com.xw.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/index")
    @RequiresRoles("admin")
    @RequiresPermissions("user:query")
    public String index() {
        // SecurityUtils.getSubject().checkRole("admin");
        return "user";
    }

}
