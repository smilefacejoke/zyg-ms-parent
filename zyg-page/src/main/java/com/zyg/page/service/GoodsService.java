package com.zyg.page.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.page.entity.GoodsEntity;


import java.util.Map;

/**
 * 商品
 *
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
 */
public interface GoodsService extends IService<GoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateStatus(String status, String[] ids);
}

