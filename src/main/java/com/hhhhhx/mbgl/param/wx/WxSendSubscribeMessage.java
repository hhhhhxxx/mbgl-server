package com.hhhhhx.mbgl.param.wx;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class WxSendSubscribeMessage implements Serializable {
    private Integer userId;
    private String drugName;
    private String advice;
    private String num;
    private String info;
    private LocalDateTime time;
    private Long timeLong;
}
