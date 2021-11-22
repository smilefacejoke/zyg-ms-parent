package com.zyg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 涛哥
 * @date 2021/11/22
 */

@SpringBootApplication
@MapperScan("com.zyg.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class,args);
    }

}
