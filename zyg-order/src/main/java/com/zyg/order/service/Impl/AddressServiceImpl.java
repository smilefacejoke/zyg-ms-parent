package com.zyg.order.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyg.order.dao.TbAddressMapper;
import com.zyg.order.entity.TbAddress;
import com.zyg.order.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 涛哥
 * @date 2022/1/5
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private TbAddressMapper addressMapper;

    /**
     * 根据用户名查找地址列表
     * @param username
     * @return
     */
    @Override
    public List<TbAddress> findAddressByUserId(String username) {
        return addressMapper.selectList(new QueryWrapper<TbAddress>().eq("user_id",username));
    }
}
