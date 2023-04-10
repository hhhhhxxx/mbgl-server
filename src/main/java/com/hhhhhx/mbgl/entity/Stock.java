package com.hhhhhx.mbgl.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Data
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
     * 生产日期
     */
    private LocalDateTime prodTime;

    /**
     * 入库时间
     */
    private LocalDateTime inTime;

    /**
     * 预警等级  	1：未过期 	2 ：半年过期 	3 ：一个月后过期  	4 ：已过期 	10：低库存少于50
     */
    private Integer level;

    /**
     * 批次号
     */
    private String batchId;

    @Version
    private Integer version;
}
