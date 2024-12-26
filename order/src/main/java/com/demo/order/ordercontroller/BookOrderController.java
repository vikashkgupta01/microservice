package com.demo.order.ordercontroller;

import com.demo.order.bookclient.IBookClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookOrder")
public class BookOrderController {

    private Logger logger = LoggerFactory.getLogger(BookOrderController.class);

    private static final String ORDER_SERVICE ="orderService" ;

    @Autowired
    IBookClient bookClient;

    /*@GetMapping("/test/{data}")
    public ResponseEntity<String> test(@PathVariable String data){
        ResponseEntity<String> flag=bookClient.testConnectivity(data);
        System.out.println(flag);
        return new ResponseEntity<>("Order running!!!", HttpStatus.OK);
    }
*/
    @Autowired
    private ServletWebServerApplicationContext webServerAppContext;




    @CircuitBreaker(name = "book", fallbackMethod = "getBookList")
    @GetMapping("/bookStock/{authorName}")
    @RateLimiter(name=ORDER_SERVICE, fallbackMethod = "rateLimiterFallback")
    /**
        RateLimitter is more better way to handle fallbackMethod
     **/
    public ResponseEntity<String> checkBookAvailability(@PathVariable String authorName) {

        // Get the port the application is running on
        int port = webServerAppContext.getWebServer().getPort();
        logger.info("Application started at port: " + port);

        // Get the list of books from the book client service
        ResponseEntity<List<Book>> bookListResponse = bookClient.getBookList(authorName);

        // If the response is null, throw an exception
        if (bookListResponse == null || bookListResponse.getBody() == null) {
            throw new RuntimeException("Error fetching book list");
        }

        // Extract the book list from the response
        Optional<List<Book>> optionalBookList = Optional.ofNullable(bookListResponse.getBody());

        // Return a ResponseEntity based on the result of the Optional
        return optionalBookList
                .map(data -> {
                    if (data.size() > 0) {
                        return new ResponseEntity<>("Book Available", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
                    }
                })
                .orElse(new ResponseEntity<>("Not Present", HttpStatus.NOT_FOUND));
    }


    public ResponseEntity<String> getBookList(String authorName, Throwable throwable) {
        // Fallback logic (this will be called when the circuit breaker is triggered)
        logger.warn("inside circuit breaker!!!");
        return new ResponseEntity<>("Fallback response due to error", HttpStatus.SERVICE_UNAVAILABLE);
    }



    public ResponseEntity<String> rateLimiterFallback(Exception e){
        logger.warn("TOO MANY API CALL!!!!");
        return new ResponseEntity<String>("order service does not permit further calls", HttpStatus.TOO_MANY_REQUESTS);

    }

    @GetMapping("/hi")
    public String getMessage(){
        return "hello";
    }
}


/*
public ResponseEntity<String> checkBookAvailbilty(@PathVariable String authorName){

        int port = webServerAppContext.getWebServer().getPort();

        logger.info("Application  started at ::"+port);

        ResponseEntity<List<Book>> bookListResponse=bookClient.getBookList(authorName);
        if(bookListResponse==null){
            throw new RuntimeException();
        }
        Optional<List<Book>> optionalBookList = Optional.ofNullable(bookListResponse.getBody());
        //return optionalBookList.ifPresentOrElse(data-> System.out.println("book available"),()-> System.out.println("Not Present"));
        return optionalBookList.map(data->{
            if(data.size()>0) {
                return new ResponseEntity<>("Book Available", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
            }
        }).orElse(new ResponseEntity<>("Not Found",HttpStatus.BAD_REQUEST));

    }


    public ResponseEntity<String> checkBookAvailbilty1(@PathVariable String authorName){

        int port = webServerAppContext.getWebServer().getPort();

        logger.info("Application  started at ::"+port);

        ResponseEntity<List<Book>> bookListResponse=bookClient.getBookList(authorName);
        if(bookListResponse==null){
            throw new RuntimeException();
        }
        Optional<List<Book>> optionalBookList = Optional.ofNullable(bookListResponse.getBody());
        //return optionalBookList.ifPresentOrElse(data-> System.out.println("book available"),()-> System.out.println("Not Present"));
        return optionalBookList.map(data->{
            if(data.size()>0) {
                return new ResponseEntity<>("Book Available", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Not Found", HttpStatus.OK);
            }
        }).orElse(new ResponseEntity<>("Please check request",HttpStatus.BAD_REQUEST));
    }
 */
