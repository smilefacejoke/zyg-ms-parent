package com.zyg.shop.dao;

import com.zyg.shop.entity.ItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表
 * 
 * @author taotao
 * @email handsomezwt@gmail.com
 * @date 2021-11-26 17:17:28
 */
@Mapper
public interface ItemDao extends BaseMapper<ItemEntity> {
	
}
