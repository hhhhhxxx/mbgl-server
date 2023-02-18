package com.hhhhhx.mbgl.entity.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hhhhhx.mbgl.massage.EnumClass;
import com.hhhhhx.mbgl.massage.value.SystemValue;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @version 1.0.0
 * @description: 返回数据
 * @date 2022/1/24
 */
@Data
@Accessors(chain = true)
public class RestResponse<T> {
    // 响应码
    private Integer code;
    private String message;
    private T data;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime time;

    // 构造函数
    public RestResponse() {
        this.time = LocalDateTime.now();
    }

    public RestResponse(Integer code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

    public RestResponse(Integer code, String message, T data) {
        this(code,message);
        this.data = data;
    }

    public RestResponse(EnumClass enumClass) {
        this(enumClass.getCode(),enumClass.getMessage());
    }


    public static <T> RestResponse<T> ok() {
        return new RestResponse<T>(SystemValue.OK);
    }

    public static <T> RestResponse<T> ok(T data) {
        return new RestResponse<T>(SystemValue.OK).setData(data);
    }

    public static <T> RestResponse<T> ok(EnumClass enumClass) {
        return new RestResponse<T>(enumClass);
    }

    public static <T> RestResponse<T> ok(EnumClass enumClass,T data) {
        return new RestResponse<T>(enumClass).setData(data);
    }

    public static <Object> RestResponse<Object> ok(Integer code, String message) {
        return new RestResponse<Object>(code,message);
    }



    public static <T> RestResponse<T> fail(T data) {
        return new RestResponse<T>(SystemValue.FAIL).setData(data);
    }

    public static <T> RestResponse<T> fail() {
        return new RestResponse<T>(SystemValue.FAIL);
    }

    public static <T> RestResponse<T> fail(EnumClass enumClass) {
        return new RestResponse<T>(enumClass);
    }

    public static <T> RestResponse<T> fail(EnumClass enumClass,T data) {
        return new RestResponse<T>(enumClass).setData(data);
    }

    public static <Object> RestResponse<Object> fail(Integer code, String message) {
        return new RestResponse<Object>(code,message);
    }
}
