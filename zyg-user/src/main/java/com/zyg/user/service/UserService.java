package com.zyg.user.service;

import com.zyg.user.entity.TbUser;

/**
 * @author 涛哥
 * @date 2021/12/16
 */
public interface UserService {
    void createCode(String phone);

    boolean isExistsCode(String validcode, String phone);

    void add(TbUser user);
}
