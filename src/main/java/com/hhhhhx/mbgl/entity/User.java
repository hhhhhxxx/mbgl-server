package com.hhhhhx.mbgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-12
 */
@TableName("t_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色类型  0管理员 1患者 2 医生 3 员工
     */
    private Integer roleId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别  0 女 1男
     */
    private Integer sex;

    /**
     * 手机
     */
    private String phone;

    /**
     * 患者住址
     */
    private String address;


    /**
     * 患者病史
     */
    private String history;

    /**
     * 患者过敏史
     */
    private String allergy;

    /**
     * 医生科室
     */
    private String room;

    /**
     * 医生医龄
     */
    private Integer jobYear;

    /**
     * 医生职位
     */
    private String position;

    /**
     * 医生医院
     */
    private String hospital;

    /**
     * 头像地址
     */
    private String image;

    /**
     * 微信唯一id
     */
    private String openId;
}
