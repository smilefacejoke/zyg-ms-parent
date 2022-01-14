package com.zyg.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 涛哥
 * @date 2022/1/4
 */
@SpringBootApplication
@EnableFeignClients
public class ZygOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZygOrderApplication.class);
    }
}
/**/