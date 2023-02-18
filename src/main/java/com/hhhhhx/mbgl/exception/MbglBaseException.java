package com.hhhhhx.mbgl.exception;



public class MbglBaseException extends RuntimeException {

    public MbglBaseException() {
    }

    public MbglBaseException(String message) {
        super(message);
    }

    public MbglBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MbglBaseException(Throwable cause) {
        super(cause);
    }

    public MbglBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
