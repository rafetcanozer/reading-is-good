package com.bookstore.readingisgood.service;

import com.bookstore.readingisgood.dto.request.BookCreateRequestDto;
import com.bookstore.readingisgood.dto.response.BookCreateResponseDto;
import com.bookstore.readingisgood.dto.response.BookUpdateStockValueResponseDto;
import com.bookstore.readingisgood.entity.Book;

import java.util.List;

public interface BookService {
    BookCreateResponseDto createBook(BookCreateRequestDto bookCreateRequestDto);

    BookUpdateStockValueResponseDto updateBookStockValue(Long bookId, Integer stock);

    Book getBookHavingEnoughStock(Long bookId, Integer stock);

    void saveBooks(List<Book> bookList);
}
