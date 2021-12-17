package com.zyg.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.user.entity.TbUser;

/**
 * @author 涛哥
 * @date 2021/12/16
 */
public interface TbUserService extends IService<TbUser> {

    void sendCode(String phone);

}
