package com.zyg.search.controller;

import com.zyg.search.entity.ItemVo;
import com.zyg.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author 涛哥
 * @date 2021/12/6
 */
@Controller
public class SerchController {

    @Autowired
    private ItemSearchService searchService;

    //1. 映射默认页面
    @GetMapping({"/","/search.html","/search"})
    //  vo：用于查询参数
    public String index(Model model,ItemVo vo){
        //1.1 通过前端传入的参数查询到数据
        Map<String,Object> resultMap=searchService.search(vo);
        //1.2 将查询到的结果放到model中
        model.addAttribute("resultMap",resultMap);
        System.out.println("resultMap = " + resultMap);
        model.addAttribute("vo",vo);
        //1.3 返回逻辑视图
        return "search";
    }


}
