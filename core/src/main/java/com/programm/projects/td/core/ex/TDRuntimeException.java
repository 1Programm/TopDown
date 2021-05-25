package com.programm.projects.td.core.ex;

public class TDRuntimeException extends RuntimeException{

    public TDRuntimeException() {
    }

    public TDRuntimeException(String message) {
        super(message);
    }

    public TDRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TDRuntimeException(Throwable cause) {
        super(cause);
    }
}
