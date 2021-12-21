package com.zyg.canal;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 涛哥
 * @date 2021/12/21
 */
@SpringBootApplication
@EnableCanalClient
public class ZygCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZygCanalApplication.class);
    }
}