package com.demo.book.exception;


public class ErrorResponse {

    private final String errorCode;

    private final String errorDescriptionCode;

    public ErrorResponse(String errorCode, String errorDescriptionCode){
        this.errorCode=errorCode;
        this.errorDescriptionCode=errorDescriptionCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescriptionCode() {
        return errorDescriptionCode;
    }
}
