package com.zyg.user.controller;

import com.zyg.common.utils.R;
import com.zyg.user.client.ManagerClient;
import com.zyg.user.entity.TbUser;
import com.zyg.user.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * ------------------------------
 * 功能：
 * 作者：WF
 * 微信：hbxfwf13590332912
 * 创建时间：2021/12/13-14:33
 * ------------------------------
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ManagerClient managerClient;

//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping({"/","register.html"})
    public String index(){

        return "register";

    }
    // 根据手机号获取验证码
    @GetMapping("/user/getCode")
    public String register(String phone){
        userService.createCode(phone);
        return "register";
    }

    //添加用户
    @PostMapping("/user/add")
    public String add(TbUser user,String validcode){
        System.out.println("user = " + user);
        //3.1 验证验证码是否存在
        boolean b = userService.isExistsCode(validcode,user.getPhone());
        System.out.println("validcode = " + validcode);
        if(b){
            userService.add(user);
        }
        //应该到登录页面
        return "register";
    }

    // 查询zyg-manager微服务的所有品牌
    @GetMapping("user/brand/list")
    @ResponseBody
    public R findAll(){
        return managerClient.findAll();
    }


}
