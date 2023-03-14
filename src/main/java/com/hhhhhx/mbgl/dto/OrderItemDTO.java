package com.hhhhhx.mbgl.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItemDTO  implements Serializable {

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

    private Integer price;

    // 额外字段
    private String name;

    private String image;
}
