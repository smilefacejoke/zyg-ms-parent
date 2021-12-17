package com.zyg.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 涛哥
 * @date 2021/12/16
 */
@RabbitListener(queues = "shz")
@Component
public class MyListener7 {

    @RabbitHandler
    public void getMessage(String msg){
        System.out.println("shz = " + msg);
    }
}