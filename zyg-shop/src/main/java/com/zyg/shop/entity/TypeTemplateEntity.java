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
 * @date 2021-11-26 17:17:27
 */
@Data
@TableName("tb_type_template")
public class TypeTemplateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 关联规格
	 */
	private String specIds;
	/**
	 * 关联品牌
	 */
	private String brandIds;
	/**
	 * 自定义属性
	 */
	private String customAttributeItems;

}
