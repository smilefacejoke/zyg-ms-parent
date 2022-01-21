package com.zyg.user.controller;

import com.zyg.common.entity.TbPayLog;
import com.zyg.common.utils.R;
import com.zyg.user.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 涛哥
 * @date 2022/1/6
 */
@RestController
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    /**
     * 添加支付日志
     * @param payLog
     * @return
     */
    @PostMapping("/user/paylog/add")
    public R add(@RequestBody TbPayLog payLog){
        payLogService.add(payLog);
        return R.ok();
    }

    /**
     * 修改支付日志
     */
    @PostMapping("/user/paylog/update")
    public R update(@RequestBody TbPayLog payLog){
        payLogService.update(payLog);
        return R.ok();
    }

    /**
     *
     */
    @GetMapping("user/paylog/findById/{outTradeNo}")
    public TbPayLog findById(@PathVariable String outTradeNo){
        return payLogService.findById(outTradeNo);
    }

}
