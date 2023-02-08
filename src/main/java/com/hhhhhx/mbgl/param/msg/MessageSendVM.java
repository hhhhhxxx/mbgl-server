package com.hhhhhx.mbgl.param.msg;

import lombok.Data;


@Data
public class MessageSendVM {
        private String content;
        private Integer sendUserId;
        private Integer receiveUserId;
}
