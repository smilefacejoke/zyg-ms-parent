package com.zyg.cart.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.zyg.cart.client.ManagerClient;
import com.zyg.cart.entity.ItemEntity;
import com.zyg.cart.entity.TbOrderItem;
import com.zyg.cart.entity.group.Cart;
import com.zyg.cart.service.CartService;
import com.zyg.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 涛哥
 * @date 2021/12/30
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ManagerClient managerClient;

    @Autowired
    private IdWorker idWorker;

    //添加商品到购物车(放到redis）
    @Override
    public List<Cart> addCart(Long itemId, int num, String username) {
        List<Cart> cartList=null;
        //根据登录使用的用户名得到redis中的购物车列表消息
        String cartListStr=redisTemplate.opsForValue().get(username);
        //判定是否为空
        if(StrUtil.isNotBlank(cartListStr)){
            //如果不为空就转换为LIst<Cart>并将商品添加到数据库
            cartList = JSON.parseArray(cartListStr, Cart.class);
        }else{
            //第一次访问时，构造一个空集合
            cartList=new ArrayList<>();
        }

        //根据商品id,找到商品对象
        ItemEntity item= managerClient.findById(itemId+"");
        System.out.println("item = " + item);
        //得到商家id
        String sellerId = item.getSellerId();
        //根据商家id，查询出购物车对象
        Cart cart=findCartBySellerId(cartList,sellerId);

        //判定是否存在购物车
        //如果存在，此时分两种情况：
        //1：判定购买的商品是否在当前购物车的选项中，如果在，就修改商品数量并添加到购物车列表中，如果不在就创建sku商品并添加到购物项列表中
        if(cart!=null){
            //如果存在就修改商品数量及金额
            //从购物车列表中找到商品，然后修改商品数量及金额
            //如果在购物车的购物列表中有此商品就修改数额及金额
            //如果没有 就直接添加到购物列表中
            updateOrderItem(cart,item,num);

        }else{
            //如果不存在,就创建订单项商品，添加到购物车中
            TbOrderItem orderItem = createOrderItem(item,num);
            //创建购物车，并把订单项放到新的购物车中
            cart=createCart(orderItem,item);
            //添加购物车到购物车列表
            cartList.add(cart);
        }

        //如果一个购物车中的所以订单项个数为0，就从购物车列表中删除此购物车
        if(cart.getOrderItemList().size()==0){
            cartList.remove(cart);
        }

        //将购物车添加到redis在
        redisTemplate.opsForValue().set(username,JSON.toJSONString(cartList));

        return cartList;
    }


    /**
     * 从redis中得到购物车列表
     * @param username
     * @return
     */
    @Override
    public List<Cart> findCartList(String username) {
        return JSON.parseArray(redisTemplate.opsForValue().get(username),Cart.class);
    }



    //修改购物车中的商品及数量
    private void updateOrderItem(Cart cart, ItemEntity item, int num) {
        //查找是否在购物车的商品列表中有此商品
        TbOrderItem tbOrderItem=null;
        for(TbOrderItem orderItem:cart.getOrderItemList()){
            if(orderItem.getItemId().longValue()==item.getId()){
                tbOrderItem=orderItem;
                break;
            }
        }
        //根据是否找到购物项商品，从而决定是修改商品还是添加商品到购物项列表中
        if(tbOrderItem!=null){
            //修改商品数量
            tbOrderItem.setNum(tbOrderItem.getNum()+num);
            //修改商品金额
            tbOrderItem.setTotalFee(new BigDecimal(tbOrderItem.getNum() * tbOrderItem.getPrice().doubleValue()));
        }else{
            //没有该商品就创建它
            TbOrderItem orderItem=createOrderItem(item,num);
            //再将此商品放到购物车列表中
            cart.getOrderItemList().add(orderItem);
        }

        //如果商品数量为0，就从购物车中的商品列表中删除它
        if(tbOrderItem.getNum()==0){
            cart.getOrderItemList().remove(tbOrderItem);
        }

    }

    //创建购物车
    private Cart createCart(TbOrderItem orderItem, ItemEntity item) {
        //新建购物车
        Cart cart=new Cart();
        //新建一个新的订单项集合
        List<TbOrderItem> orderItems=new ArrayList<>();
        //将这个订单项对象放到上面的集合中
        orderItems.add(orderItem);
        //将这个订单项集合绑定到购物车中
        cart.setOrderItemList(orderItems);
        //设置购物车的其他项
        cart.setSellerId(orderItem.getSellerId());
        //设置商家名字
        cart.setSellerName(item.getSeller());
        //返回购物车
        return cart;
    }


    //创建订单项商品
    private TbOrderItem createOrderItem(ItemEntity item, int num) {
        //定义订单项id
        long id = idWorker.nextId();
        TbOrderItem orderItem=new TbOrderItem();
        //设置订单项的属性
        orderItem.setId(id);
        orderItem.setNum(num);
        orderItem.setTitle(item.getTitle());
        orderItem.setPrice(item.getPrice());
        orderItem.setItemId(item.getId());
        orderItem.setGoodsId(item.getGoodsId());
        orderItem.setSellerId(item.getSellerId());
        orderItem.setPicPath(item.getImage());
        orderItem.setTotalFee(BigDecimal.valueOf(item.getNum() * item.getPrice().doubleValue()));
        //返回订单项
        return orderItem;
    }

    private Cart findCartBySellerId(List<Cart> cartList, String sellerId) {
        if(cartList.size()>0){
            for(Cart cart:cartList){
                if(cart.getSellerId().equals(sellerId)){
                    return cart;
                }
            }
        }
        return null;
    }
}
