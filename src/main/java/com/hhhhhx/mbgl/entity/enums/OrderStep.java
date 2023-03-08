package com.hhhhhx.mbgl.entity.enums;

public enum OrderStep {
    // 有操作
    PAY(1,"已付款，等待发货"),
    // 普通通知 无操作
    SEND(2,"已发货"),
    TRANSPORT(3,"运输中"),
    INFO(3,"运输中"),
    END(5,"运输中");


    Integer code;
    String name;

    OrderStep(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
