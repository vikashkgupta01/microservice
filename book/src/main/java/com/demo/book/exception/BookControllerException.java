package com.demo.book.exception;

public class BookControllerException extends RuntimeException {

    private final String errorCode;

    private final String errorDescription;


    public BookControllerException(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
