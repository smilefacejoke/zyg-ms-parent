package com.zyg.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.shop.entity.GoodsEntity;
import com.zyg.shop.entity.group.Goods;

import java.util.Map;

/**
 * 
 *
 * @author taotao
 * @email handsomezwt@gmail.com
 * @date 2021-11-26 17:17:27
 */
public interface GoodsService extends IService<GoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(Goods goods);

    Goods findById(String id);

    void update(Goods goods);
}

