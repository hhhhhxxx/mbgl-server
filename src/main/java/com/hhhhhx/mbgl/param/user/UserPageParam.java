package com.hhhhhx.mbgl.param.user;

import com.hhhhhx.mbgl.param.BasePage;
import lombok.Data;

@Data
public class UserPageParam extends BasePage {
    private String username;
    private Integer roleId;
}
