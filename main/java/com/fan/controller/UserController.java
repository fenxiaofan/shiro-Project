package com.fan.controller;

import com.fan.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @RequestMapping(value = "/sublogin", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public String subLogin(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName()
                ,user.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        if(subject.hasRole("admin")) {
            return "有admin权限";
        }

        return "登陆成功";
    }

    //采用注解方式使用roles，如果是权限的话采用注解@RequiresPermission("XXX")
    @RequiresRoles("admin")
    @RequestMapping(value = "/testrole", method = RequestMethod.GET)
    @ResponseBody
    public String testRole() {
        return "testRole success";
    }
}
