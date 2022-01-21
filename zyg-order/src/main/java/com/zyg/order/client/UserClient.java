package com.zyg.order.client;

import com.zyg.common.entity.TbPayLog;
import com.zyg.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 涛哥
 * @date 2022/1/6
 */
@FeignClient("zyg-user")
public interface UserClient {

    @PostMapping("/user/paylog/add")
    public R add(@RequestBody TbPayLog payLog);

    @GetMapping("/user/paylog/findById/{outTradeNo}")
    TbPayLog findById(@PathVariable("outTradeNo") String outTradeNo);

    @PostMapping("/user/paylog/update")
    R update(@RequestBody TbPayLog payLog);
}
