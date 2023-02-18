package com.hhhhhx.mbgl.param.msg;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class MessageSendVM {
        private String content;
        @NotNull
        private Integer sendUserId;
        @NotNull
        private Integer receiveUserId;
}
