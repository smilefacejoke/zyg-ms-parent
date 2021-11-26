package com.zyg.shop.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author taotao
 * @email handsomezwt@gmail.com
 * @date 2021-11-26 17:17:28
 */
@Data
@TableName("tb_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 品牌名称
	 */
	private String name;
	/**
	 * 品牌首字母
	 */
	private String firstChar;

}
