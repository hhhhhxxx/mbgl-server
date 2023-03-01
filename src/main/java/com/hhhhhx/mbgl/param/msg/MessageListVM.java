package com.hhhhhx.mbgl.param.msg;

import com.hhhhhx.mbgl.param.BasePage;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class MessageListVM {
    @NotNull
    private Integer pageSize;
    @NotNull
    private Integer sendUserId;
    @NotNull
    private Integer receiveUserId;

    private LocalDateTime targetTime;
}
