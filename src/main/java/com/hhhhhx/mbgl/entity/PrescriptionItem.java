package com.hhhhhx.mbgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hhhhhx
 * @since 2023-03-16
 */
@TableName("t_prescription_item")
@Data
public class PrescriptionItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer prescriptionId;

    private Integer drugId;

    private Integer quantity;

}
