package com.deukyun.realworld.common.exception;

public class RealworldRuntimeException extends RuntimeException {

    public RealworldRuntimeException() {
        super();
    }

    public RealworldRuntimeException(String message) {
        super(message);
    }

    public RealworldRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RealworldRuntimeException(Throwable cause) {
        super(cause);
    }

    protected RealworldRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
