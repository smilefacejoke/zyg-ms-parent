package com.zyg.user.client;

import com.zyg.common.utils.R;
import com.zyg.user.client.factory.ManagerClientFallbackFactory;
import com.zyg.user.client.impl.ManagerClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 涛哥
 * @date 2021/12/22
 * 服务调用组件：openFeign底层使用动态代理实现
 */
@FeignClient(value = "zyg-manager",fallbackFactory = ManagerClientFallbackFactory.class)
public interface ManagerClient {

    /**
     * 1. 查询所有的品牌
     * @return
     */
    @GetMapping("manager/brand/findAll")
    public R findAll();

}
