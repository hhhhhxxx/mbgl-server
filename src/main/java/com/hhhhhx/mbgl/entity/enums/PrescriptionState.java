package com.hhhhhx.mbgl.entity.enums;

public enum PrescriptionState {
    NO_USE(0),
    USE(1);

    Integer code;

    PrescriptionState(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
