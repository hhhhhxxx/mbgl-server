package com.hhhhhx.mbgl.param.user;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserLoginParam {
    @NotNull
    private String username;
    @NotNull
    @Length(min = 3,max = 20)
    private String password;
}
