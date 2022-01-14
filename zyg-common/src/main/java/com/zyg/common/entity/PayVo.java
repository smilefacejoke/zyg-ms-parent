package com.zyg.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * ------------------------------
 * 功能：
 * 作者：WF
 * 微信：hbxfwf13590332912
 * 创建时间：2021/8/16-16:34
 * ------------------------------
 */
@Data
public class PayVo implements Serializable {
    private String out_trade_no; // 商户订单号 必填
    private String subject; // 订单名称 必填
    private String total_amount;  // 付款金额 必填
    private String body; // 商品描述 可空
}
