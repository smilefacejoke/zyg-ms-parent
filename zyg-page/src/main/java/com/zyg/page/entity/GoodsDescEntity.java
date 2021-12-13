package com.zyg.page.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
 */
@Data
@TableName("tb_goods_desc")
public class GoodsDescEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * SPU_ID
	 */
	@TableId
	private String goodsId;
	/**
	 * 描述
	 */
	private String introduction;
	/**
	 * 规格结果集，所有规格，包含isSelected
	 */
	private String specificationItems;
	/**
	 * 自定义属性（参数结果）
	 */
	private String customAttributeItems;
	/**
	 * 
	 */
	private String itemImages;
	/**
	 * 包装列表
	 */
	private String packageList;
	/**
	 * 售后服务
	 */
	private String saleService;

}
