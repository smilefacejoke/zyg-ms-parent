package com.zyg.page.controller;

import com.zyg.common.utils.R;
import com.zyg.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 涛哥
 * @date 2021/12/10
 */
@RestController
@RequestMapping
public class Pagecontroller {

    @Autowired
    private PageService pageService;

    @GetMapping("/createHtml/{id}")
    public R createHtml(@PathVariable Long id) throws IOException {
        pageService.createHtml(id);
        return R.ok();
    }


}
