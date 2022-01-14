package com.zyg.order.service;

import com.zyg.order.entity.TbAddress;

import java.util.List;

/**
 * @author 涛哥
 * @date 2022/1/5
 */
public interface AddressService {
    List<TbAddress> findAddressByUserId(String username);
}
