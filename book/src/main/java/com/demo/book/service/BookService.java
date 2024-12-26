package com.demo.book.service;

import com.demo.book.common.ProcessContent;
import com.demo.book.dto.BookDto;
import com.demo.book.entity.Book;
import com.demo.book.exception.BookServiceException;
import com.demo.book.repository.BookRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BookService {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    BookRepo bookRepo;

    @Autowired
    private CacheManager cacheManager;

    //@Autowired
    private TxnRefGenerationSerive txnRefGenerationSerive;

    //@Autowired
    private ProcessContent content;

    // Constructor injection, Spring will automatically wire these dependencies
    @Autowired
    public BookService(TxnRefGenerationSerive txnRefGenerationSerive, ProcessContent content) {
        this.txnRefGenerationSerive = txnRefGenerationSerive;
        this.content = content;
    }

    //@Transactional
    public Book execute(BookDto bookDto){
        txnRefGenerationSerive.execute();

        try {
            Book book = new Book();

            //book.setId(bookDto.getId());
            book.setPrice(bookDto.getPrice());
            book.setAuthorName(bookDto.getAuthorName());
            book.setRating(bookDto.getRating());
            book.setBookName(bookDto.getBookName());

            book.setCreatedDt(LocalDate.now().toString());
            book.setLastModifiedDt(LocalDate.now().toString());
            book.setTransRef(content.getFlowParams().get("transRef")+"");

            logger.info("Inside Book Service!!!!");

            return bookRepo.save(book);
        }catch (BookServiceException bookServiceException){
            throw new BookServiceException(bookServiceException.getErrorReason(), bookServiceException.getErrorDescription());
        }catch (Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }


    public List<Book> saveBulk(List<BookDto> bookDtos){
        //System.out.println("content ::"+content);
        txnRefGenerationSerive.execute();
        List<Book> books=new ArrayList<>();
        for(BookDto bookDto:bookDtos){
            Book book=new Book();
            book.setPrice(bookDto.getPrice());
            book.setAuthorName(bookDto.getAuthorName());
            book.setRating(bookDto.getRating());
            book.setBookName(bookDto.getBookName());
            book.setGenre(bookDto.getGenre());

            /*book.setCreatedDt(LocalDate.now().toString());
            book.setLastModifiedDt(LocalDate.now().toString());*/

            book.setTransRef(content.getFlowParams().get("transRef")+"");
            //System.out.println("Transref ::"+content.getFlowParams().get("transRef"));
            books.add(book);


        }

        content.setBook(books);
        System.out.println(content.getBook());
        /*for(Book book:books){
            System.out.println(book);
        }*/
        return bookRepo.saveAll(books);

    }

    /*public Optional<List<Book>> getBook(String bookName){
        Optional<List<Book>> checkBookAvailabilty= bookRepo.findByAuthorName(bookName);
        return Optional.ofNullable(checkBookAvailabilty.orElseThrow().stream().toList());
    }*/

    @Cacheable(value = "booksRecord", key="#authorName")
    public List<Book> getBook(String authorName)throws InterruptedException {
        // Attempt to retrieve books by author name
        //Thread.sleep(1000000);
        logger.info("Inside Book Service!!!");
        List<Book> books = bookRepo.findByAuthorName(authorName)
                .orElseThrow(() -> new RuntimeException("Books not available for the author: " + authorName));
        return books; // Directly return the list of books
    }

    @Cacheable(value = "allBooks", key="#result")
    public int getAllBook(){
        int record=bookRepo.findAll().size();
        return record;
    }

//Need some more R&D here
    public void printCacheContents() {
        Cache cache = cacheManager.getCache("result");

        /*if (cache != null) {
            // Print the cache size and content for a specific key
            Map<Object, Object> cacheableMap= (Map<Object, Object>) cache.getNativeCache();

            // Optionally, print all the cache keys and their values
            cacheableMap.entrySet().forEach(entry -> {


                if (entry.getValue() instanceof List<?>) {
                    List<Book> books = (List<Book>) entry.getValue();
                    logger.info("Cache Key: " + entry.getKey() + " has " + books.size() + " books.");
                }
            });
        }else{
            logger.info("no data in cache currently!!!");
        }*/


        /*logger.info("Cache type: " + cache.getNativeCache().getClass().getName());

        // Attempt to cast to Map (if possible)
        Object nativeCache = cache.getNativeCache();

        if (nativeCache instanceof Map) {
            Map<Object, Object> cacheableMap = (Map<Object, Object>) nativeCache;
            // Print all cache keys and values
            cacheableMap.entrySet().forEach(entry -> {
                if (entry.getValue() instanceof List<?>) {
                    List<Book> books = (List<Book>) entry.getValue();
                    logger.info("Cache Key: " + entry.getKey() + " has " + books.size() + " books.");
                } else {
                    logger.info("Cache Key: " + entry.getKey() + " has value: " + entry.getValue());
                }
            });
        } else {
            logger.warn("Cache is not of type Map, unable to process entries.");
        }*/

        if (cache != null) {
            // Get the value from the cache using the key ("allBooks" in this case)
            Cache.ValueWrapper valueWrapper = cache.get("allBooks");

            // Check if the value exists in the cache
            if (valueWrapper != null) {
                // Retrieve the list of books from the cache (value of type List<Book>)
                List<Book> books = (List<Book>) valueWrapper.get();

                // Print the size of the cache and the list of books
                System.out.println("Cache contains " + books.size() + " books.");
                books.forEach(book -> {
                    // Print the details of each book
                    System.out.println("Book ID: " + book.getId() +
                            ", Title: " + book.getBookName() +
                            ", Author: " + book.getAuthorName());
                });
            } else {
                System.out.println("No data found in cache for key 'allBooks'.");
            }
        } else {
            System.out.println("Cache 'booksRecords' does not exist.");
        }

    }




    public void printCache(String cacheName){
        Cache cache=cacheManager.getCache(cacheName);
        if(cache!=null){
            Map<Object, Object> cacheableMap= (Map<Object, Object>) cache.getNativeCache();
            logger.info("Map size is ::"+ cacheableMap.entrySet().size());
            //cacheableMap.entrySet().forEach(data-> System.out.println(data.getKey()+" ::"+data.getValue()));
            /*for(Map.Entry<Object, Object> entry:cacheableMap.entrySet()){
                System.out.println(entry.getKey()+" ::"+entry.getValue());
                System.out.println();
            }*/

            cacheableMap.entrySet().forEach(entry -> {


                if (entry.getValue() instanceof List<?>) {
                    List<Book> books = (List<Book>) entry.getValue();
                    logger.info("Cache Key: " + entry.getKey() + " has " + books.size() + " books.");
                }
            });
        }else{
            logger.info("no data in cache currently!!!");
        }
    }

}
