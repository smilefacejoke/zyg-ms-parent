package com.zyg.order.controller;

import com.zyg.common.entity.TbPayLog;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
     * 添加订单
     */
    @PostMapping("/order/add")
    public String add(TbOrder order){
        //获取登录用户名
        String username = getUsername();

        orderService.save(order,username);
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
