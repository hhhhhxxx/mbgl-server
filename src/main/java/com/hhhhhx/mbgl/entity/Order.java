package com.hhhhhx.mbgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@TableName("t_order")
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

    /**
     * 是否付款 0没有 1有
     */
    private Integer pay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }
    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", userId=" + userId +
            ", address=" + address +
            ", addressName=" + addressName +
            ", addressPhone=" + addressPhone +
            ", pay=" + pay +
        "}";
    }
}
