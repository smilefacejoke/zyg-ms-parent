package com.zyg.user.service;

import com.zyg.user.entity.TbUser;

/**
 * Created by WF on 2021/12/13 14:43
 */
public interface UserService {
    void createCode(String phone);

    boolean isExistsCode(String validcode, String phone);

    void add(TbUser user);
}
