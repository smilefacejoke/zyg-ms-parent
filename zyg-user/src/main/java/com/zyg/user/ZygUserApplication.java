package com.zyg.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ------------------------------
 * 功能：
 * 作者：WF
 * 微信：hbxfwf13590332912
 * 创建时间：2021/12/13-14:37
 * ------------------------------
 */
@SpringBootApplication
@EnableFeignClients
public class ZygUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZygUserApplication.class);
    }
}
