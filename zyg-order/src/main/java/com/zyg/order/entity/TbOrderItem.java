package com.zyg.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by WF on 2021/11/12 14:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_order_item")
public class TbOrderItem implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 商品id
     */
    @TableField(value = "item_id")
    private Long itemId;

    /**
     * SPU_ID
     */
    @TableField(value = "goods_id")
    private Long goodsId;

    /**
     * 订单id
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 商品标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 商品单价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 商品购买数量
     */
    @TableField(value = "num")
    private Integer num;

    /**
     * 商品总金额
     */
    @TableField(value = "total_fee")
    private BigDecimal totalFee;

    /**
     * 商品图片地址
     */
    @TableField(value = "pic_path")
    private String picPath;

    @TableField(value = "seller_id")
    private String sellerId;

    private static final long serialVersionUID = 1L;
}