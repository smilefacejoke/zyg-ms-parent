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
@TableName("tb_specification_option")
public class SpecificationOptionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 规格项ID
	 */
	@TableId
	private String id;
	/**
	 * 规格项名称
	 */
	private String optionName;
	/**
	 * 规格ID
	 */
	private String specId;
	/**
	 * 排序值
	 */
	private Integer orders;

}
