package com.zyg.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 涛哥
 * @date 2021/12/30
 */
@SpringBootApplication
@EnableFeignClients
public class ZygCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZygCartApplication.class);
    }
}
