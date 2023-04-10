package com.hhhhhx.mbgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@TableName("t_order")
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 购买用户id
     */
    private Integer userId;

    /**
     * 地址
     */
    private String address;

    /**
     * 收件人姓名
     */
    private String addressName;

    /**
     * 收件人手机号
     */
    private String addressPhone;

    // 区域
    private String addressArea;

    // 进度
    private Integer step;
    /**
     * 是否付款 0没有 1有
     */
    private Integer pay;

    private LocalDateTime createTime;

    public Order() {
        createTime = LocalDateTime.now();
    }
}
