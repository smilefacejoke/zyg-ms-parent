package com.zyg.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
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
