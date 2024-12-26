package com.demo.book.repository;

import com.demo.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    Optional<List<Book>> findByAuthorName(String authorName);
}
