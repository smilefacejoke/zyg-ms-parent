package com.zyg.order.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyg.common.entity.TbPayLog;
import com.zyg.common.utils.IdWorker;
import com.zyg.order.client.UserClient;
import com.zyg.order.dao.TbOrderItemMapper;
import com.zyg.order.dao.TbOrderMapper;
import com.zyg.order.entity.Cart;
import com.zyg.order.entity.TbOrder;
import com.zyg.order.entity.TbOrderItem;
import com.zyg.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 涛哥
 * @date 2022/1/5
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper orderItemMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserClient userClient;


    /**
     * 从redis中获取购物车
     * @param username
     * @return
     */
    @Override
    public List<Cart> findCartList(String username) {
        return JSON.parseArray(redisTemplate.opsForValue().get(username),Cart.class);
    }

    /**
     * 保存订单
     * @param order
     * @param username
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class,timeoutMills = 60000)
    @Transactional
    public void save(TbOrder order, String username) {
        //从redis中得到购物车列表
        List<Cart> cartList=findCartList(username);

        //定义存放订单id的集合
        List<String> ids=new ArrayList<>();

        double total=0;

        //遍历购物车
        for(Cart cart:cartList){
            //part one:添加订单对象
            //构造要添加的订单对象
            TbOrder newOrder=new TbOrder();
            //生成订单id
            Long id=idWorker.nextId();
            ids.add(id+"");
            //为订单设置属性
            newOrder.setOrderId(id);
            //1：代表未付款，2代表已付款
            newOrder.setStatus("1");
            //数据来源：2：代表来自pc端
            newOrder.setSourceType("2");
            newOrder.setUserId(username);
            //商家id
            newOrder.setSellerId(cart.getSellerId());
            //支付类型
            newOrder.setPaymentType(order.getPaymentType());
            newOrder.setUpdateTime(new Date());
            newOrder.setCreateTime(new Date());
            newOrder.setReceiverMobile(order.getReceiverMobile());
            //设置联系人
            newOrder.setReceiver(order.getReceiver());
            //设置联系地址
            newOrder.setReceiverAreaName(order.getReceiverAreaName());

            //订单总金额
            double sum=0;

            //part two:添加订单项
            for(TbOrderItem orderItem:cart.getOrderItemList()){
                //设置订单项属性
                //设置订单属性
                orderItem.setOrderId(id);
                orderItem.setId(idWorker.nextId());
                orderItem.setSellerId(cart.getSellerId());
                //添加所有订单项的总金额
                sum += orderItem.getTotalFee().doubleValue();
                //添加订单项
                orderItemMapper.insert(orderItem);
            }

            total=total+sum;

            //part three:添加订单
            //设置订单总金额
            newOrder.setPayment(new BigDecimal(sum));
            orderMapper.insert(newOrder);

            }
        //part four:添加日志
        if("1".equals(order.getPaymentType())){
            //定义一个tbPaylog对象
            TbPayLog payLog=new TbPayLog();
            payLog.setOutTradeNo(idWorker.nextId()+"");
            //得到订单id
            String orderIds=ids.toString().replace("[","").replace("]","");
            payLog.setOrderList(orderIds);

            payLog.setCreateTime(new Date());
            payLog.setPayType("2");
            payLog.setTotalFee((long)total);
            //0代表未支付，1代表已支付
            payLog.setTradeState("0");
            payLog.setUserId(username);
            userClient.add(payLog);
//                payLogMapper.insert(payLog);

            //添加到redis中
            redisTemplate.opsForValue().set("paylog:"+username,JSON.toJSONString(payLog));
        }
    }

    @Override
    public TbPayLog getPayLogFromRedis(String username) {
        return JSON.parseObject(redisTemplate.opsForValue().get("paylog:"+username),TbPayLog.class);
    }

    /**
     * 根据登录名获取订单及订单项列表
     * @param username
     * @return
     */
    @Override
    public List<TbOrder> getOrderList(String username) {
        //得到登录用户的订单列表
        List<TbOrder> orders = orderMapper.selectList(new QueryWrapper<TbOrder>().eq("user_id",username));
        //为登录用户的订单项列表设置值
        for(TbOrder order : orders){
            //根据订单id（外键）查询出订单项列表
            List<TbOrderItem> orderItems=orderItemMapper.selectList(new QueryWrapper<TbOrderItem>().eq("order_id",order.getOrderId()));
            //与订单对象进行绑定
            order.setOrderItems(orderItems);
        }
        //返回
        return orders;

    }

    @Override
    public TbOrder findById(String id) {
        return orderMapper.selectById(id);
    }

    @Override
    public void update(TbOrder order) {
        orderMapper.updateById(order);
    }


}
