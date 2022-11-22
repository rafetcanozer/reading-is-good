package com.bookstore.readingisgood.repository;

import com.bookstore.readingisgood.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitleAndAuthorAndPublishYear(String title, String author, Integer publishYear);

}
