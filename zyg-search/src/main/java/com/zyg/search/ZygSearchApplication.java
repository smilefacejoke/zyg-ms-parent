package com.zyg.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ------------------------------
 * 功能：
 * 作者：WF
 * 微信：hbxfwf13590332912
 * 创建时间：2021/12/3-9:44
 * ------------------------------
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZygSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZygSearchApplication.class);
    }
}
