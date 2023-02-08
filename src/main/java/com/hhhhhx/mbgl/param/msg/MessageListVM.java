package com.hhhhhx.mbgl.param.msg;

import com.hhhhhx.mbgl.param.BasePage;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageListVM {
    private Integer pageSize;
    private Integer sendUserId;
    private Integer receiveUserId;

    private LocalDateTime targetTime;
}
