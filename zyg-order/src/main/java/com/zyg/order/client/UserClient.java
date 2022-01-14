package com.zyg.order.client;

import com.zyg.common.entity.TbPayLog;
import com.zyg.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
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

}
