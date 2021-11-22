package com.zyg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @TableField(exist = false)      //代表数据库中不存在该数据
    private String cname;

    public Student(String name, String sex, Integer age, String addr, Integer cid) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.addr = addr;
        this.cid = cid;
    }
}
