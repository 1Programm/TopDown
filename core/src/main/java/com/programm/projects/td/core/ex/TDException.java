package com.programm.projects.td.core.ex;

public class TDException extends Exception{

    public TDException() {
    }

    public TDException(String message) {
        super(message);
    }

    public TDException(String message, Throwable cause) {
        super(message, cause);
    }

    public TDException(Throwable cause) {
        super(cause);
    }
}
