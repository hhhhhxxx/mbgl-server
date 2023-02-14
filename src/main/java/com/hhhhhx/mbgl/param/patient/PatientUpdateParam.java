package com.hhhhhx.mbgl.param.patient;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Data
public class PatientUpdateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer id;

    private String name;

    private Integer age;

    private Integer sex;

    private String phone;

    private String address;

    private String history;

    private String allergy;

    private String image;
}
