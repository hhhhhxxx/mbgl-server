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
 * @since 2023-04-09
 */
@TableName("t_drug_type")
@Data
public class DrugType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 药品种类名称
     */
    private String name;
}
