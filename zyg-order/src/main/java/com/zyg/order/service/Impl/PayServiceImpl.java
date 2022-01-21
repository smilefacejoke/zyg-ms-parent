package com.zyg.order.service.Impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.zyg.common.entity.PayAsyncVo;
import com.zyg.common.entity.TbPayLog;
import com.zyg.common.utils.AlipayTemplate;
import com.zyg.order.client.UserClient;
import com.zyg.order.entity.TbOrder;
import com.zyg.order.service.OrderService;
import com.zyg.order.service.PayService;
import io.seata.spring.annotation.GlobalTransactional;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 涛哥
 * @date 2022/1/21
 */
@Service
public class PayServiceImpl  implements PayService {

    @Autowired
    private AlipayTemplate alipayTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserClient userClient;

    /**
     * 进行验签处理
     * @param vo
     * @param params
     * @return
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class,timeoutMills = 60000)
    @Transactional
    public String validSignature(PayAsyncVo vo, Map<String, String> params) throws AlipayApiException {

        //进行验签处理
        boolean b= AlipaySignature.rsaCheckV1(params,alipayTemplate.getAlipay_public_key(),alipayTemplate.getCharset(),alipayTemplate.getSign_type());

        if (b){
            //如果成功则进行正常的业务逻辑

            //第一部分: 修改支付日志
            //根据登录名得到支付日志
            TbPayLog payLog=userClient.findById(vo.getOut_trade_no());
            //修改支付日志中的数据
            //添加支付时间
            payLog.setPayTime(new Date());
            //修改订单状态为1，代表已支付
            payLog.setTradeState("1");
            //修改流水号
            payLog.setTransactionId(vo.getTrade_no());
            //进行更新操作
            userClient.update(payLog);

            //第二部分: 更新订单状态
            if("TRADE_SUCCESS".equals(vo.getTrade_status())||"TRADE_FINISHED".equals(vo.getTrade_status())){
                //得到id订单
                String orderList=payLog.getOrderList();
                //对其进行拆分获取id
                for(String id : orderList.split(",")){
                    //得到订单
                    TbOrder order=orderService.findById(id);
                    //修改订单状态为已支付
                    order.setStatus("2");
                    //保存订单
                    orderService.update(order);
                }
            }
            return "success";
        }

        return "fail";
    }
}
