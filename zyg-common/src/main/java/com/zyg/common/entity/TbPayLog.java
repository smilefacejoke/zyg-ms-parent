package com.zyg.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WF on 2021/11/19 14:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_pay_log")
public class TbPayLog implements Serializable {
    /**
     * 支付订单号
     */
    @TableId(value = "out_trade_no", type = IdType.INPUT)
    private String outTradeNo;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 支付完成时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 支付金额（分）
     */
    @TableField(value = "total_fee")
    private Long totalFee;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 交易号码
     */
    @TableField(value = "transaction_id")
    private String transactionId;

    /**
     * 交易状态
     */
    @TableField(value = "trade_state")
    private String tradeState;

    /**
     * 订单编号列表
     */
    @TableField(value = "order_list")
    private String orderList;

    /**
     * 支付类型
     */
    @TableField(value = "pay_type")
    private String payType;

    private static final long serialVersionUID = 1L;
}