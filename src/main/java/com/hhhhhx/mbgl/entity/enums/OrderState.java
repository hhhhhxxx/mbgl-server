package com.hhhhhx.mbgl.entity.enums;

public enum OrderState {
    HAS_PAY(1),
    NO_PAY(2),
    CANCEL(3);

    Integer code;

    OrderState(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
