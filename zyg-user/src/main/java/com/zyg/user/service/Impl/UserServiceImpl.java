package com.zyg.user.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.zyg.user.dao.TbUserMapper;
import com.zyg.user.entity.TbUser;
import com.zyg.user.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * ------------------------------
 * 功能：
 * 作者：WF
 * 微信：hbxfwf13590332912
 * 创建时间：2021/12/13-14:44
 * ------------------------------
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private TbUserMapper userMapper;


    /**
     * 生成验证，同时发送信息给阿里大于【利用RabbitMQ发送消息】
     * 将生成的验证放到redis中（以手机号为key）
     * @param phone
     */
    @Override
    public void createCode(String phone) {
        // 生成一个随机的验证码
        int min = 100000;
        int max = 999999;
        Random random = new Random();
        int code = random.nextInt(max-min);
        if(code < min){
            code = code + min ;
        }
        System.out.println("生成的六位数的验证码是：" + code);

        //将随机数保存到redis中
        redisTemplate.opsForValue().set(phone,code + "",10, TimeUnit.DAYS);

        //通过rabbitmq发送消息给消息的监听方
        // 组织数据
        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("code",code + "");
        //1.3.2 发送数据
        rabbitTemplate.convertAndSend("sms",map);

    }

    // 校验验证码查否合法
    @Override
    public boolean isExistsCode(String validcode, String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if(StrUtil.isNotBlank(code)) {
            assert code != null;
            return code.equals(validcode);
        }
        return false;
    }

    // 添加用户
    @Override
    public void add(TbUser user) {
        // 获取加密密码
        String s = SecureUtil.md5(user.getPassword());
        // 设置原密码
        user.setPassword(s);
        // 保存用户
        userMapper.insert(user);
    }
}
