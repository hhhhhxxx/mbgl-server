package com.hhhhhx.mbgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 费用表
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@TableName("t_charge")
public class Charge implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 花费
     */
    private Integer cost;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 支付方式
     */
    private Integer payment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Charge{" +
            "id=" + id +
            ", cost=" + cost +
            ", orderId=" + orderId +
            ", payment=" + payment +
        "}";
    }
}
