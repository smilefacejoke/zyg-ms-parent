package com.zyg.order.entity;

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
 * Created by WF on 2021/11/12 14:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_address")
public class TbAddress implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 省
     */
    @TableField(value = "province_id")
    private String provinceId;

    /**
     * 市
     */
    @TableField(value = "city_id")
    private String cityId;

    /**
     * 县/区
     */
    @TableField(value = "town_id")
    private String townId;

    /**
     * 手机
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 详细地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 联系人
     */
    @TableField(value = "contact")
    private String contact;

    /**
     * 是否是默认 1默认 0否
     */
    @TableField(value = "is_default")
    private String isDefault;

    /**
     * 备注
     */
    @TableField(value = "notes")
    private String notes;

    /**
     * 创建日期
     */
    @TableField(value = "create_date")
    private Date createDate;

    /**
     * 别名
     */
    @TableField(value = "alias")
    private String alias;

    private static final long serialVersionUID = 1L;
}