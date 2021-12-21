package com.zyg.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

/**
 * Created by WF on 2021/11/8 15:37
 */

/**
    * 用户表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_user")
public class TbUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码，加密存储
     */
    @TableField(value = "password")
    private String password;

    /**
     * 注册手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 注册邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 创建时间
     */
    @TableField(value = "created")
    private Date created;

    @TableField(value = "updated")
    private Date updated;

    /**
     * 会员来源：1:PC，2：H5，3：Android，4：IOS，5：WeChat
     */
    @TableField(value = "source_type")
    private String sourceType;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 真实姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 使用状态（Y正常 N非正常）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 头像地址
     */
    @TableField(value = "head_pic")
    private String headPic;

    /**
     * QQ号码
     */
    @TableField(value = "qq")
    private String qq;

    /**
     * 账户余额
     */
    @TableField(value = "account_balance")
    private Long accountBalance;

    /**
     * 手机是否验证 （0否  1是）
     */
    @TableField(value = "is_mobile_check")
    private String isMobileCheck;

    /**
     * 邮箱是否检测（0否  1是）
     */
    @TableField(value = "is_email_check")
    private String isEmailCheck;

    /**
     * 性别，1男，2女
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 会员等级
     */
    @TableField(value = "user_level")
    private Integer userLevel;

    /**
     * 积分
     */
    @TableField(value = "points")
    private Integer points;

    /**
     * 经验值
     */
    @TableField(value = "experience_value")
    private Integer experienceValue;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 最后登录时间
     */
    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123");
        System.out.println("encode = " + encode);
    }
}