package com.hhhhhx.mbgl.exception;

import com.hhhhhx.mbgl.massage.EnumClass;
import com.hhhhhx.mbgl.massage.value.SystemValue;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MbglServiceException extends MbglBaseException {

    private Integer code;
    private String message;



    public MbglServiceException(EnumClass enumClass) {
        this.code = enumClass.getCode() == null ?
                SystemValue.FAIL.getCode() : enumClass.getCode();
        this.message = enumClass.getMessage() == null ?
                SystemValue.FAIL.getMessage() : enumClass.getMessage();
    }

    public MbglServiceException(EnumClass enumClass, String message) {
        super(message);
        this.code = enumClass.getCode() == null ?
                SystemValue.FAIL.getCode() : enumClass.getCode();
        this.message = enumClass.getMessage() == null ?
                SystemValue.FAIL.getMessage() : enumClass.getMessage();
    }

    public MbglServiceException() {
        this.code = SystemValue.FAIL.getCode();
        this.message = SystemValue.FAIL.getMessage();
    }

}
