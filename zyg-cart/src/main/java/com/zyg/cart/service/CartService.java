package com.zyg.cart.service;

import com.zyg.cart.entity.group.Cart;

import java.util.List;

/**
 * @author 涛哥
 * @date 2021/12/30
 */
public interface CartService {
    List<Cart> addCart(Long itemId, int num, String username);

    List<Cart> findCartList(String username);
}
