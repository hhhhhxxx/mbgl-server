package com.hhhhhx.mbgl.massage;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnumClass {

    Integer code;
    String massage;

    public EnumClass(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public static EnumClass bulid(Integer code, String massage) {
        return new EnumClass(code,massage);
    }

}
