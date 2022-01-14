package com.zyg.user.controller;

import com.zyg.common.entity.TbPayLog;
import com.zyg.common.utils.R;
import com.zyg.user.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 涛哥
 * @date 2022/1/6
 */
@RestController
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    @PostMapping("/user/paylog/add")
    public R add(@RequestBody TbPayLog payLog){
        payLogService.add(payLog);
        return R.ok();
    }

}
