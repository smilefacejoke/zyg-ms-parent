package com.zyg.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 品牌管理
 * 
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-23 10:27:07
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
