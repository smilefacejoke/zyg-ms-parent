package com.zyg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.manager.entity.ItemCatEntity;

import java.util.Map;

/**
 * 商品类目
 *
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
 */
public interface ItemCatService extends IService<ItemCatEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

