package com.zyg.order.controller;

import com.alipay.api.AlipayApiException;
import com.zyg.common.entity.PayVo;
import com.zyg.common.entity.TbPayLog;
import com.zyg.common.utils.AlipayTemplate;
import com.zyg.common.utils.R;
import com.zyg.order.client.UserClient;
import com.zyg.order.entity.Cart;
import com.zyg.order.entity.TbAddress;
import com.zyg.order.entity.TbOrder;
import com.zyg.order.service.AddressService;
import com.zyg.order.service.OrderService;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 涛哥
 * @date 2022/1/4
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserClient userClient;

    @Autowired
    private AlipayTemplate alipayTemplate;

    /**
     * 默认页面
     * @return
     */
    @GetMapping({"/","getOrderInfo"})
    public String getOrderInfo(Model model){
        //获取登录用户名
        String username = getUsername();
        //prat one:显示登录用户的地址列表
        //根据用户名查询地址列表
        List<TbAddress> addressList=addressService.findAddressByUserId(username);
        //将地址列表放到model中
        model.addAttribute("addressList",addressList);

        //part two：显示登录用户的购物车内容
        List<Cart> cartList=orderService.findCartList(username);
        model.addAttribute("cartList",cartList);

        return "getOrderInfo";
    }

    /**
     * 显示支付宝的支付页面的表单
     */
    @GetMapping("order/pay/{type}/{outTradeNo}")
    @ResponseBody
    public String orderPay(@PathVariable int type,@PathVariable String outTradeNo) throws AlipayApiException {
        //从redis中根据用户登录名得到支付日志并构造出Payvo对象
        String username=getUsername();
        //根据用户名，从redis中得到支付日志并转换薇payvo
        TbPayLog payLog=orderService.getPayLogFromRedis(username);

        PayVo vo=new PayVo();
        vo.setOut_trade_no(payLog.getOutTradeNo());
        vo.setTotal_amount(payLog.getTotalFee()+"");
        vo.setSubject("商城支付订单");
        vo.setBody("你好");
        //得到返回的支付表单页面
        String result = alipayTemplate.pay(vo, type);
        return result;
    }



    /**
     * 添加订单
     */
    @PostMapping("/order/add")
    public String add(TbOrder order,Model model){
        //获取登录用户名
        String username = getUsername();
        orderService.save(order,username);
        //从redis中得到支付对象
        TbPayLog payLog=orderService.getPayLogFromRedis(username);
        //将支付日志放到model中
        model.addAttribute("payLog",payLog);
        return "pay";
    }

    @PostMapping("/payLog/add")
    public R payLogAdd(@RequestBody TbPayLog payLog){
        userClient.add(payLog);
        return R.ok();
    }



    private String getUsername() {
        //获取用户名
        AttributePrincipal principal=(AttributePrincipal)request.getUserPrincipal();
        return principal.getName();
    }




}
