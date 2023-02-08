package com.hhhhhx.mbgl.entity.enums;

public enum DataType {
    HEART_RATE(1,"心率"),
    STEP_COUNT(2,"步数"),
    BLOOD_PRESSURE(3,"血压"),
    BLOOD_SUGAR(4,"血糖");

    Integer code;
    String name;

    DataType(Integer code, String name) {
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
