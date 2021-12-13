package com.zyg.page.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.page.entity.GoodsDescEntity;

import java.util.Map;

/**
 * 
 *
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
 */
public interface GoodsDescService extends IService<GoodsDescEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

