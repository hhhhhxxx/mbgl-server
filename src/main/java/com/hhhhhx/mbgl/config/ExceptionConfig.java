package com.hhhhhx.mbgl.config;

import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.massage.EnumClass;
import com.hhhhhx.mbgl.massage.value.SystemValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionConfig {


    /**
     * 参数为实体类
     * @param e
     * @return
     */
    @ExceptionHandler(value = MbglServiceException.class)
    public RestResponse<Object> handleValidException(MbglServiceException e) {
        // 从异常对象中拿到ObjectError对象
        EnumClass enumClass = e.getEnumClass();


        // 然后提取错误提示信息进行返回
        return RestResponse.fail(enumClass);
    }

    /**
     * 参数为实体类
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RestResponse<Object> handleValidException(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return RestResponse.fail(SystemValue.PARAM_ERROR,objectError.getDefaultMessage());
    }

    /**
     * 参数为单个参数或多个参数
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public RestResponse<Object> handleConstraintViolationException(ConstraintViolationException e) {
        // 从异常对象中拿到ObjectError对象
        String message = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()).get(0);

        return RestResponse.fail(SystemValue.PARAM_ERROR,message);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public RestResponse<Object> noHandlerFoundExceptionHandler(HttpServletRequest request, NoHandlerFoundException e) {
        log.error("未找到{}",request.getContextPath());
        return RestResponse.fail(SystemValue.CLIENT_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponse<Object> globalException(HttpServletRequest request, Exception e) {
        log.error("发生错误{}",request.getContextPath());
        log.error("错误内容{}",e.getMessage());
        return RestResponse.fail(SystemValue.SERVER_ERROR);
    }
}
