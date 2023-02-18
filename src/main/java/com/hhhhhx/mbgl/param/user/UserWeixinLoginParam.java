package com.hhhhhx.mbgl.param.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserWeixinLoginParam {
    @NotNull
    private String code;
}
