package com.hhhhhx.mbgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 库存表
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@TableName("t_stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 药品id
     */
    private Integer drugId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 生成日期
     */
    private LocalDateTime prodTime;

    /**
     * 入库时间
     */
    private LocalDateTime inTime;

    /**
     * 预警等级  	1：未过期 	2 ：半年过期 	2 ：一个月后过期  	4 ：已过期 	10：低库存少于50
     */
    private Integer level;

    /**
     * 批次号
     */
    private String batchId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public LocalDateTime getProdTime() {
        return prodTime;
    }

    public void setProdTime(LocalDateTime prodTime) {
        this.prodTime = prodTime;
    }
    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        return "Stock{" +
            "id=" + id +
            ", drugId=" + drugId +
            ", quantity=" + quantity +
            ", prodTime=" + prodTime +
            ", inTime=" + inTime +
            ", level=" + level +
            ", batchId=" + batchId +
        "}";
    }
}
