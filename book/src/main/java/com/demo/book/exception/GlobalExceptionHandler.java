package com.demo.book.exception;

import com.demo.book.controller.BookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(BookController.class);


    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions1(MethodArgumentNotValidException ex) {
        // Collecting validation error messages for each invalid field
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        // Return the list of error messages as the response
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(BookServiceException.class)
    public ResponseEntity<Map<String, String>> handleBookServiceException(BookServiceException bookServiceException) {
        // Prepare a map to hold the error response
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorReason", bookServiceException.getErrorReason());
        errorResponse.put("errorDescription", bookServiceException.getErrorDescription());

        // Return the error response with a BAD_REQUEST status (or another suitable HTTP status)
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Validation failed: {}", ex.getBindingResult());
        // Collect validation error messages
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        // Return the validation errors as a BAD_REQUEST response
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        logger.error("Inside Generic Exception!!!",ex.getMessage());
        System.out.println(ex.getMessage());
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
/*@ExceptionHandler(BookServiceException.class)
    public ResponseEntity<String> handleBookServcieException(BookServiceException bookServiceException){
        return new ResponseEntity<>(bookServiceException.getErrorReason(), bookServiceException.getErrorDescription());
    }*//*


    @ExceptionHandler(BookServiceException.class)
    public ResponseEntity<Map<String, String>> handleBookServiceException(BookServiceException bookServiceException) {
        // Prepare a map to hold the error response
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorReason", bookServiceException.getErrorReason());
        errorResponse.put("errorDescription", bookServiceException.getErrorDescription());

        // Return the error response with a BAD_REQUEST status (or another suitable HTTP status)
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }




}


/*

@RestControllerAdvice
public class GlobalExceptionHandler {

    //private org.slf4j.Logger logger = LoggerFactory.getLogger(BookController.class);

    private Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Validation failed: {}", ex.getBindingResult());
        // Collect validation error messages
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        // Return the validation errors as a BAD_REQUEST response
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<String> handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
        logger.error("Caught HandlerMethodValidationException: {}", ex.getMessage());
        return new ResponseEntity<>("Method validation failed: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        logger.error("Inside Generic Exception: {}", ex.getMessage());
        logger.error("Caught : {}", ex.getClass().getSimpleName());
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
*/

/*
@RestControllerAdvice
public class ExceptionHandler
        extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders httpHeaders, HttpStatus httpStatus,
            WebRequest webRequest){

        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", httpStatus.value());

        // Get all errors
        List<String> exceptionalErrors
                = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        objectBody.put("Errors", exceptionalErrors);

        return new ResponseEntity<>(objectBody, httpStatus);
    }
}*/
