package com.hhhhhx.mbgl.param.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUploadImageParam {
    @NotNull
    private Integer id;
    @NotNull
    private String image;
}
