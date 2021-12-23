package com.zyg.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 涛哥
 * @date 2021/12/22
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZygGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZygGatewayApplication.class);
    }
}
