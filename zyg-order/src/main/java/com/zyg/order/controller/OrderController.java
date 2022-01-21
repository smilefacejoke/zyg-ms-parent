package com.zyg.order.controller;

import com.alipay.api.AlipayApiException;
import com.zyg.common.entity.PayAsyncVo;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private com.zyg.order.service.PayService payService;

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

    /**
     * 根据当前登录用户，查询此用户的所有订单列表
     * 支付宝支付 同步回调方法
     * @return
     */
    @GetMapping("/order/getOrderList")
    public String getOrderList(Model model){
        // 5.1 获取登录名
        String username = getUsername();
        // 5.2 获取此登录名对应的订单列表
        List<TbOrder> orderList = orderService.getOrderList(username);
        // 5.3 将订单列表放到model中
        model.addAttribute("orderlist",orderList);

        return "orderlist";
    }

    /**
     * 支付宝异步回调
     * @return
     */
    @PostMapping("/order/pay")
    @ResponseBody
    public String syncCallback(PayAsyncVo vo,HttpServletRequest request) throws AlipayApiException {
        System.out.println("vo = " + vo);
        //得到登录用户名
//        String username=getUsername();
        //得到支付包后台传入的数据
        Map<String,String> params=new HashMap<>();
        Map<String,String[]> requestParams=request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name,valueStr);
        }
        //进行验签处理
        String result = payService.validSignature(vo,params);
        System.out.println("result = " + result);
        //返回
        return result;
    }


    private String getUsername() {
        //获取用户名
        AttributePrincipal principal=(AttributePrincipal)request.getUserPrincipal();
        return principal.getName();
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }




}
