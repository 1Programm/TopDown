package com.programm.projects.td.core.ex;

public class TDFatalException extends TDException{

    public TDFatalException() {
    }

    public TDFatalException(String message) {
        super(message);
    }

    public TDFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TDFatalException(Throwable cause) {
        super(cause);
    }
}
