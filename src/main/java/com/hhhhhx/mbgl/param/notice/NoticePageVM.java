package com.hhhhhx.mbgl.param.notice;

import com.hhhhhx.mbgl.param.BasePage;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NoticePageVM extends BasePage {
    @NotNull
    private Integer state;
    @NotNull
    private Integer userId;
}
