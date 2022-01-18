package com.zyg.order.service;

import com.zyg.common.entity.TbPayLog;
import com.zyg.order.entity.Cart;
import com.zyg.order.entity.TbOrder;

import java.util.List;

/**
 * @author 涛哥
 * @date 2022/1/4
 */
public interface OrderService {

    List<Cart> findCartList(String username);

    void save(TbOrder order, String username);

    TbPayLog getPayLogFromRedis(String username);

}
