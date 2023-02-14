package com.hhhhhx.mbgl.entity;

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
public class DoctorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer id;

    private String name;

    private Integer age;

    private Integer sex;

    private Integer phone;

    private String room;

    private Integer jobYear;

    private String position;

    private String hospital;

    private String image;
}
