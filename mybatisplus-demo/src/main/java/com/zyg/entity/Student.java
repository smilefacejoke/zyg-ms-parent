package com.zyg.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 涛哥
 * @date 2021/11/22
 */

@Data
@TableName("student")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    // 定义一个主键是自动增长的主键
    @TableId(type = IdType.AUTO)
    private Integer sid;
    private String name;
    private String sex;
    private Integer age;
    private String addr;
    private Integer cid;

    @TableField(fill = FieldFill.INSERT)        //代表插入数据是自动填充
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)        //代表插入/修改数据是自动填充
    private LocalDateTime updateTime;


    @TableField(exist = false)      //代表数据库中不存在该数据
    private String cname;

    //逻辑删除
    @TableLogic            //默认1代表删除，0代表存在
    private Integer deleted;

    public Student(String name, String sex, Integer age, String addr, Integer cid) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.addr = addr;
        this.cid = cid;
    }
}
