package com.zyg.cart.controller;

import com.zyg.cart.entity.group.Cart;
import com.zyg.cart.service.CartService;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @author 涛哥
 * @date 2021/12/30
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping({"/","/cart.html"})
    public String index(Model model){
        //获取用户名（Cas登录名的获取方式）
        AttributePrincipal principal=(AttributePrincipal)request.getUserPrincipal();
        String username=principal.getName();
        //根据登录名，得到购物车列表
        List<Cart> cartList = cartService.findCartList(username);
        //放到model视图中
        model.addAttribute("cartList",cartList);

        return "cart";
    }


    /**
     *添加购物车
     */
    @GetMapping("/addCart")
    public String addCart(Long itemId, int num, Model model){
        //获取CAS的使用的登录名
        AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
        String username = principal.getName();
        //添加商品到购物车
        List<Cart> cartList = cartService.addCart(itemId,num,username);
        //将购物车列表放到redis中
        model.addAttribute("cartList",cartList);
        return "cart";

   }

}
