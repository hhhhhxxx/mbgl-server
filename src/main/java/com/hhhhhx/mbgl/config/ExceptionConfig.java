package com.hhhhhx.mbgl.config;

import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.massage.WebStatusEnumClazz;
import com.hhhhhx.mbgl.massage.value.SystemValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionConfig {


    @Value(value = "${spring.profiles.active}")
    private String env;

    private final static String ENV_DEV = "dev";

    private static final String EXCEPTION_LOG_FORMAT = "expection:{},stackTrace：{},message:{}";



    /**
     * ServiceException异常
     */
    @ExceptionHandler(MbglServiceException.class)
    public RestResponse<Object> handlerServiceException(MbglServiceException e) {
        log.error(EXCEPTION_LOG_FORMAT, e.getClass().getName(), getStackTraceFromException(e), e.getMessage());
        StringBuffer buffer = new StringBuffer(e.getMessage());
        if (StringUtils.isNotEmpty(e.getMessage())) {
            buffer.append("[").append(e.getMessage()).append("]");
        }
        return RestResponse.fail(e.getCode(),e.getMessage());
    }

    /**
     * 拦截请求参数注解产生的异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RestResponse<Object> validExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        // getFieldError获取的是第一个不合法的参数(如果有多个参数不合法的话)
        FieldError fieldError = bindingResult.getFieldError();
        String msg = null;
        if (fieldError != null) {
            if (Objects.equals(env, ENV_DEV)) {
                String field = fieldError.getField();
                msg = "参数校验失败：" + field + "-" + fieldError.getDefaultMessage();
            } else {
                msg = fieldError.getDefaultMessage();
            }
        }
        StringBuilder message = new StringBuilder();
        for (ObjectError e : ex.getBindingResult().getAllErrors()) {
            if (message.length() == 0) {
                message.append(e.getDefaultMessage());
            } else {
                message.append(",").append(e.getDefaultMessage());
            }
        }
        log.error(EXCEPTION_LOG_FORMAT, ex.getClass().getName(), getStackTraceFromException(ex), ex.getMessage());
        return RestResponse.fail((Integer) WebStatusEnumClazz.PARAM_ERROR.getCode(), WebStatusEnumClazz.PARAM_ERROR.getMessage() + ":" + msg);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public RestResponse<Object> handleConstraintViolationException(ConstraintViolationException e) {
        log.error(EXCEPTION_LOG_FORMAT, e.getClass().getName(), getStackTraceFromException(e), e.getMessage());
        return RestResponse.fail((Integer) WebStatusEnumClazz.PARAM_ERROR.getCode(), WebStatusEnumClazz.PARAM_ERROR.getMessage() + ":" + generateResponseData(e));
    }

    /**
     * 参数为空异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public RestResponse<Object> requestMissingServletRequest(Exception e) {
        log.error(EXCEPTION_LOG_FORMAT, e.getClass().getName(), getStackTraceFromException(e), e.getMessage());
        return RestResponse.fail((Integer) WebStatusEnumClazz.PARAM_ERROR.getCode(), WebStatusEnumClazz.PARAM_ERROR.getMessage() + ":" + generateResponseData(e));
    }




    /**
     * 处理数据绑定异常
     *
     * @param bindException 数据绑定异常
     * @return 数据绑定异常默认包
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public RestResponse<Object> handleValidateException(BindException bindException) {
        log.error(EXCEPTION_LOG_FORMAT, bindException.getClass().getName(), getStackTraceFromException(bindException), bindException.getMessage());

        StringBuilder msgBuilder = new StringBuilder();
        List<ObjectError> errors = bindException.getAllErrors();
        for (ObjectError error : errors) {
            if (!(error instanceof FieldError)) {
                continue;
            }

            FieldError fieldError = (FieldError) error;
            String field = fieldError.getField();
            String msg = fieldError.getDefaultMessage();
            msgBuilder.append(field).append(msg).append(".");
        }
        return RestResponse.fail((Integer) WebStatusEnumClazz.DATA_ERROR.getCode(), WebStatusEnumClazz.DATA_ERROR.getMessage() + ":" + msgBuilder.toString());
    }

    /**
     * 统一处理405
     *
     * @param exception 异常
     * @return 数据包
     */
    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public RestResponse<Object> handelRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        log.error(EXCEPTION_LOG_FORMAT, exception.getClass().getName(), getStackTraceFromException(exception), exception.getMessage());
        return RestResponse.fail((Integer) WebStatusEnumClazz.METHOD_NOT_SUPPORT.getCode(), WebStatusEnumClazz.METHOD_NOT_SUPPORT.getMessage() + ":" + generateResponseData(exception));
    }

    /**
     * 统一处理404
     *
     * @param exception 异常
     * @return 数据包
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.NOT_FOUND)
    public RestResponse<Object> handel404(NoHandlerFoundException exception) {
        log.error(EXCEPTION_LOG_FORMAT, exception.getClass().getName(), getStackTraceFromException(exception), exception.getMessage());
        return RestResponse.fail((Integer) WebStatusEnumClazz.NOT_FOUND.getCode(), WebStatusEnumClazz.NOT_FOUND.getMessage() + ":" + generateResponseData(exception));
    }




    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponse<Object> globalException(HttpServletRequest request, Exception e) {
        log.error("发生错误{}",request.getContextPath());
        log.error("错误内容{}",e.getMessage());
        return RestResponse.fail(SystemValue.SERVER_ERROR);
    }




    public static String getStackTraceFromException(Exception e) {
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception e2) {
            log.error(EXCEPTION_LOG_FORMAT, e2.getClass().getName(), getStackTraceFromException(e2), e2.getMessage());
        }
        return null;
    }

    public <T extends Exception> String generateResponseData(T e) {
        if (env != null && env.equals(ENV_DEV)) {
            return getStackTraceFromException(e);
        }
        return e.getMessage();
    }

    public static String getStackTraceFromThrowable(Throwable throwable) {
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception e2) {
            log.error(EXCEPTION_LOG_FORMAT + getStackTraceFromThrowable(e2), e2.getMessage());
        }

        return null;
    }

    public static String getFirstStackInfo2(Exception e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.getMessage() + System.lineSeparator());
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        StackTraceElement firstStack = stackTraceElements[0];
        stringBuilder.append(firstStack);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("fileName:" + firstStack.getFileName() + System.lineSeparator());
        stringBuilder.append("className:" + firstStack.getClassName() + System.lineSeparator());
        stringBuilder.append("methodName:" + firstStack.getMethodName() + System.lineSeparator());
        stringBuilder.append("lineNumber:" + firstStack.getLineNumber());
        return stringBuilder.toString();
    }
}
