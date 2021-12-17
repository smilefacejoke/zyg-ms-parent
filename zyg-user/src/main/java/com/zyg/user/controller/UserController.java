package com.zyg.user.controller;

import com.zyg.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 涛哥
 * @date 2021/12/16
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/","register.html"})
    public String register(String phone){
        //1. 根据手机号生成验证码
        userService.createCode(phone);

        return "register";
    }

}
