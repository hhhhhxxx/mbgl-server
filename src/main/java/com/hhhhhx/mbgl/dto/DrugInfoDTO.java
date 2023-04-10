package com.hhhhhx.mbgl.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 药品表
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@Data
public class DrugInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 药品名称
     */
    private String name;

    /**
     * 单位
     */
    private String unit;

    /**
     * 价格
     */
    private Integer price;

    // 展示价格
    private Double tempPrice;
    /**
     * 药物介绍
     */
    private String info;

    /**
     * 注意事项
     */
    private String warn;

    /**
     * 用法
     */
    private String usages;

    /**
     * 图片
     */
    private String image;

    /**
     * 有效期 单位月
     */
    private Integer validMonth;

    /**
     * 生产厂家
     */
    private String prodComp;

    /**
     * 是否处方 1处方 0非处方
     */
    private Integer prescription;

    /**
     * 药品分类 中药 西药
     */
    private Integer classification;
}
