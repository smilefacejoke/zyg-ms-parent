package com.zyg.user.service.Impl;

import com.zyg.user.entity.TbUser;
import com.zyg.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 涛哥
 * @date 2021/12/16
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 1. 生成验证，同时发送信息给阿里大于【利用RabbitMQ发送信息】
     * 2. 将生成的验证放到redis中（以手机号为key）
     * @param phone
     */
    @Override
    public void createCode(String phone) {

    }

    @Override
    public boolean isExistsCode(String validcode, String phone) {
        return false;
    }

    @Override
    public void add(TbUser user) {

    }
}
