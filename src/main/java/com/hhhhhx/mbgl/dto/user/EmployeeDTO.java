package com.hhhhhx.mbgl.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer id;

    private String name;

    private Integer age;

    private Integer sex;

    private String phone;

    private String image;

}
