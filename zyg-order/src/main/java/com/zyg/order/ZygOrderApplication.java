package com.zyg.order;

import com.zyg.common.utils.AlipayTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public AlipayTemplate alipayTemplate(){
        return new AlipayTemplate();
    }

}
/**/