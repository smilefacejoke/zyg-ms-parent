package com.zyg.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 涛哥
 * @date 2021/12/23
 */
@RestController
public class HelloController {

    @GetMapping("Hello")
    public String hello(){
        return "hello";
    }

}
