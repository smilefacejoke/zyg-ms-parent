package com.zyg.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.shop.entity.TypeTemplateEntity;

import java.util.Map;

/**
 * 
 *
 * @author taotao
 * @email handsomezwt@gmail.com
 * @date 2021-11-26 17:17:27
 */
public interface TypeTemplateService extends IService<TypeTemplateEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

