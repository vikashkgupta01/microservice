package com.demo.book.controller;

import com.demo.book.common.JsonUtil;
import com.demo.book.dto.BookDto;
import com.demo.book.entity.Book;
import com.demo.book.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    //private static final Logger logger = Logger.getLogger(BookController.class.getName());
    //private static final Logger logger= (Logger) LoggerFactory.getLogger(BookController.class);

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    /*@Value("${server.port}")
    private String serverPort;*/

    /*@Value("${server.port}")
    private String serverPort;*/

    @Autowired
    private ServletWebServerApplicationContext webServerAppContext;


    @GetMapping("/test/{name}")
    public ResponseEntity<String> getMessage(@PathVariable String name){
        return ResponseEntity.ok("running ::"+name);
        //return new ResponseEntity<>("Running!!", HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto){
        //ProcessContent content = new ProcessContent();
        String channelRequest = JsonUtil.toJson(bookDto);
        //content.getFlowParams().put("RequestPO", channelRequest);

        bookService.execute(bookDto);

        return ResponseEntity.ok(bookDto);
    }

    @PostMapping("/addBukkBook")
    public ResponseEntity<List<Book>> addBulkData(@RequestBody @Valid List<BookDto> bookDtos) {
        // Validation should work now on each BookDto inside the list
        List<Book> bookList = bookService.saveBulk(bookDtos);
        return new ResponseEntity<>(bookList, HttpStatus.CREATED);
    }

    /*@GetMapping("check/{authorName}")
    public ResponseEntity<Optional<List<Book>>> getBook(@PathVariable("authorName") String authorName){
        return  new ResponseEntity<>(bookService.getBook(authorName),HttpStatus.OK);
    }*/

    @GetMapping("check/{authorName}")
    public ResponseEntity<List<Book>> getBook(@PathVariable("authorName") String authorName) throws InterruptedException{

        int port = webServerAppContext.getWebServer().getPort();

        logger.info("Application  started at ::"+port);
        //throw new RuntimeException("Book service temporary down!!!!");
       List<Book> books = bookService.getBook(authorName);
<<<<<<< HEAD
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

=======
       bookService.printCache("booksRecord");
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/search")
    public String getALlBooks(){
        int size=0;
        bookService.printCacheContents();
        if(bookService.getAllBook()!=0){
            return "Book size is :"+size;
        }else
            return "No books found!!!";

    }

>>>>>>> bc5fa73 (First commit with root directory and submodules)

}
