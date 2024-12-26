package com.demo.book.exception;

public class BookServiceException extends RuntimeException {

    String errorReason;
    String errorDescription;

    public BookServiceException(String errorReason, String errorDescription) {
        super(errorDescription);
        this.errorReason = errorReason;
        this.errorDescription = errorDescription;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public String getErrorDescription(){
        return errorDescription;
    }

}
