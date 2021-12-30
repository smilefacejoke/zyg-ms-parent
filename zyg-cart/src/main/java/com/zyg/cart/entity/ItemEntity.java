package com.zyg.cart.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


/**
 * 商品表
 * 
 * @author WF
 * @email 2568783935@qq.com
 * @date 2021-07-23 21:10:35
 */
@Data
@TableName("tb_item")

public class ItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id，同时也是商品编号
	 */
	private Long id;
	/**
	 * 商品标题
	 */
	private String title;
	/**
	 * 商品卖点
	 */
	private String sellPoint;
	/**
	 * 商品价格，单位为：元
	 */
	private BigDecimal price;
	/**
	 * 
	 */
	private Integer stockCount;
	/**
	 * 库存数量
	 */
	private Integer num;
	/**
	 * 商品条形码
	 */
	private String barcode;
	/**
	 * 商品图片
	 */
	private String image;
	/**
	 * 所属类目，叶子类目
	 */
	private Long categoryid;
	/**
	 * 商品状态，1-正常，2-下架，3-删除
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private String itemSn;
	/**
	 * 
	 */
	private BigDecimal costPirce;
	/**
	 * 
	 */
	private BigDecimal marketPrice;
	/**
	 * 
	 */
	private String isDefault;
	/**
	 * 
	 */
	private Long goodsId;
	/**
	 * 
	 */
	private String sellerId;
	/**
	 * 
	 */
	private String cartThumbnail;
	/**
	 * keyword：不能进行分词
	 */
	private String category;
	/**
	 * 
	 */
	private String brand;
	/**
	 * 
	 */
	private String spec;
	/**
	 * 
	 */
	private String seller;

	//@Transient
	@TableField(exist = false)
	private Map<String,Object> specMap;

}
