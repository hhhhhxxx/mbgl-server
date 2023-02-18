package com.hhhhhx.mbgl.massage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class EnumClass {

    Integer code;
    String message;

    public static EnumClass create(Integer code, String message) {
        return EnumClass.builder().code(code).message(message).build();
    }
}
