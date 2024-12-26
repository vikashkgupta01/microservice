package com.demo.order.bookclient;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class BookClientFallBack implements IBookClient{

    private Logger logger = LoggerFactory.getLogger(BookClientFallBack.class);

    @Override
    public ResponseEntity<List<Book>> getBookList(String authorName) {
        logger.error("Book service temporary down!!!!");
        logger.error(String.valueOf(new ResponseEntity<>("Book service currently down!!", HttpStatus.BAD_GATEWAY)));
        return null;
    }

    /*public ResponseEntity<String> getBookList(String authorName, Throwable throwable) {
        logger.error("Fallback triggered for getBookList: " + throwable.getMessage());
        return new ResponseEntity<>("Book service is temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }*/

    /*@Override
    public ResponseEntity<String> testConnectivity(String name) {
        return null;
    }*/


    /*
    @Override
    public ResponseEntity<List<Book>> getBookList(String authorName) {
        // In case of failure, return an empty list or an appropriate response
        return ResponseEntity.status(503).body(Collections.emptyList());
    }

    // Fallback for rate limiter
    public ResponseEntity<List<Book>> rateLimiterFallback(String authorName, Throwable throwable) {
        // Return a fallback response when rate limit is exceeded
        return ResponseEntity.status(429).body(Collections.emptyList());  // 429 Too Many Requests
    }

    // Fallback for circuit breaker
    public ResponseEntity<List<Book>> circuitBreakerFallback(String authorName, Throwable throwable) {
        // Return a fallback response when circuit breaker is open
        return ResponseEntity.status(503).body(Collections.emptyList());  // 503 Service Unavailable
    }*/
}
