package com.zyg.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.shop.entity.SellerEntity;

import java.util.Map;

/**
 * 
 *
 * @author taotao
 * @email handsomezwt@gmail.com
 * @date 2021-11-26 17:17:28
 */
public interface SellerService extends IService<SellerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

