package com.zyg.page.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品类目
 * 
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
 */
@Data
@TableName("tb_item_cat")
public class ItemCatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 类目ID
	 */
	@TableId
	private String id;
	/**
	 * 父类目ID=0时，代表的是一级的类目
	 */
	private String parentId;
	/**
	 * 类目名称
	 */
	private String name;
	/**
	 * 类型id
	 */
	private String typeId;

}
