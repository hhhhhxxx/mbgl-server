package com.hhhhhx.mbgl.entity.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户角色枚举类
 * @author zuck
 */
public enum RoleEnum {
    ADMIN(0, "ADMIN"),
    PATIENT(1, "PATIENT"),
    DOCTOR(2,"DOCTOR"),
    EMPLOYEE(3,"EMPLOYEE");


    Integer code;
    String name;

    RoleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Integer, RoleEnum> keyMap = new HashMap<>();

    static {
        for (RoleEnum item : RoleEnum.values()) {
            keyMap.put(item.getCode(), item);
        }
    }

    public static RoleEnum fromCode(Integer code) {
        return keyMap.get(code);
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

    public String getRoleName() {
        return "ROLE_" + name;
    }
}
