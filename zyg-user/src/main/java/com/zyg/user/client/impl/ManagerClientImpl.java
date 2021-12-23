package com.zyg.user.client.impl;

import com.zyg.common.utils.R;
import com.zyg.user.client.ManagerClient;
import org.springframework.stereotype.Component;

/**
 * @author 涛哥
 * @date 2021/12/22
 */
@Component
public class ManagerClientImpl implements ManagerClient {
    /**
     * 1. 查询所有的品牌
     *
     * @return
     */
    @Override
    public R findAll() {
        return R.error("zyg-manager Dead by ManagerClientImpl");
    }
}
