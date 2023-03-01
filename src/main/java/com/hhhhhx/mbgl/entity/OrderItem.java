package com.hhhhhx.mbgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
            "id=" + id +
            ", orderId=" + orderId +
            ", quantity=" + quantity +
            ", stockId=" + stockId +
        "}";
    }
}
