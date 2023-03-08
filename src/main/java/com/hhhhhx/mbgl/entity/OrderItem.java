package com.hhhhhx.mbgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@TableName("t_order_item")
@Data
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 一个oder有多个item
     */
    private Integer orderId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 库存id
     */
    private Integer stockId;

    private Integer price;
}
