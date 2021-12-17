package com.zyg.manager.controller;

import com.zyg.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 涛哥
 * @date 2021/12/15
 */
@RestController
@RequestMapping
@RefreshScope
public class HelloController {
    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
    private int age;


    @GetMapping("/hello")
    public R hello(){
        return R.ok().put("name",name).put("age",age);
    }
}
