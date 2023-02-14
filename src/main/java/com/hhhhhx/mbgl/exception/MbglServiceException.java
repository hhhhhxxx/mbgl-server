package com.hhhhhx.mbgl.exception;

import com.hhhhhx.mbgl.massage.EnumClass;
import com.hhhhhx.mbgl.massage.value.SystemValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MbglServiceException extends RuntimeException {

    private EnumClass enumClass;
    /**
     * 默认是系统异常
     */
    public MbglServiceException(EnumClass enumClass) {
        super(enumClass.getMassage());
        this.enumClass = enumClass;
    }

    public MbglServiceException() {
        super(SystemValue.FAIL.getMassage());
        this.enumClass = SystemValue.FAIL;
    }

    public MbglServiceException(Integer code,String message) {
        super(message);
        EnumClass enumClass = EnumClass.bulid(code, message);
        this.enumClass = enumClass;
    }

}
