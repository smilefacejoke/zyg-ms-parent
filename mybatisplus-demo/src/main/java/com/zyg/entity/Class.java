package com.zyg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 涛哥
 * @date 2021/11/22
 */
@Data
@TableName("class")
public class Class {

    @TableId(type = IdType.AUTO)
    private Integer cid;
    private String cname;
}
