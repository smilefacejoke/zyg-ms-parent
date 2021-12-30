package com.zyg.cart.client;

import com.zyg.cart.entity.ItemEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 涛哥
 * @date 2021/12/30
 */
@FeignClient("zyg-manager")
public interface ManagerClient {

    /**
     * 根据itemId查询商品信息
     * @param id
     * @return
     */
    @GetMapping("/manager/item/findById/{id}")
    public ItemEntity findById(@PathVariable("id") String id);

}
