package com.demo.order.bookclient;

/*import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;*/
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Book;
import java.util.List;

<<<<<<< HEAD
@FeignClient(name="book")//, fallback =BookClientFallBack.class )
public interface IBookClient {

    /*@GetMapping("/book/test/{name}")
    ResponseEntity<String> testConnectivity(@PathVariable String name);*/

=======
@FeignClient(name="book", fallback =BookClientFallBack.class )
public interface IBookClient {

>>>>>>> bc5fa73 (First commit with root directory and submodules)
    @GetMapping("/book/check/{authorName}")
     ResponseEntity<List<Book>> getBookList(@PathVariable String authorName);


<<<<<<< HEAD
=======
    /*@GetMapping("/book/test/{name}")
    ResponseEntity<String> testConnectivity(@PathVariable String name);*/

>>>>>>> bc5fa73 (First commit with root directory and submodules)

    /*@RateLimiter(name = "bookServiceRateLimiter", fallbackMethod = "rateLimiterFallback")
    @CircuitBreaker(name = "bookServiceCircuitBreaker", fallbackMethod = "circuitBreakerFallback")
    @GetMapping("/book/check/{authorName}")
    ResponseEntity<List<Book>> getBookList(@PathVariable String authorName);

    // Fallback for rate limiter
    default ResponseEntity<List<Book>> rateLimiterFallback(String authorName, Throwable throwable) {
        // Return a fallback response when rate limit is exceeded
        return ResponseEntity.status(429).body(List.of());  // 429 Too Many Requests
    }

    // Fallback for circuit breaker
    default ResponseEntity<List<Book>> circuitBreakerFallback(String authorName, Throwable throwable) {
        // Return a fallback response when circuit breaker is open
        return ResponseEntity.status(503).body(List.of());  // 503 Service Unavailable
    }*/

}
