package com.demo.book.common;

import com.demo.book.entity.Book;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@Component
public class ProcessContent extends BaseContent {

    private List<Book> book;

    @Autowired
    public ProcessContent(List<Book> book) {
        this.book = book;
    }
}