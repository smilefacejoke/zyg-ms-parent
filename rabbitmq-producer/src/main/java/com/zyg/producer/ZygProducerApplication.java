package com.zyg.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 涛哥
 * @date 2021/12/16
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZygProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZygProducerApplication.class);
    }
}
