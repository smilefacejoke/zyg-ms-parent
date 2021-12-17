package com.zyg.producer.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 涛哥
 * @date 2021/12/16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送direct直连消息：此时routingkey与监听方的queue名字一样
     */
    @Test
    public void test01(){
        rabbitTemplate.convertAndSend("zwt","正在发送direct消息");

    }

    /**
     * 发送fanout（分裂消息）：此时只需要对exhange发送消息，与其关联的队列都会收到消息
     */
    @Test
    public void test02(){
        rabbitTemplate.convertAndSend("zwt-company","","发送fanout消息");
    }

    /**
     * 发送topic（主题消息）：此时只接受感兴趣的消息
     */
    @Test
    public void test03(){
        rabbitTemplate.convertAndSend("books","book.#","发送topic(book开头消息");
    }

    @Test
    public void test04(){
        rabbitTemplate.convertAndSend("books","#.logs","发送topic(logs结尾)");
    }

    @Test
    public void test05(){
        rabbitTemplate.convertAndSend("books","book.logs","发送topic(book.logs)");
    }


}
