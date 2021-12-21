package com.zyg.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.user.entity.TbUser;


/**
* Created by WF on 2021/11/8 15:37
*/
public interface TbUserService extends IService<TbUser> {

    void sendCode(String phone);


}
