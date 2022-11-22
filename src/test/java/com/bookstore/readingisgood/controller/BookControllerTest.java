package com.bookstore.readingisgood.controller;

import com.bookstore.readingisgood.dto.request.BookCreateRequestDto;
import com.bookstore.readingisgood.dto.response.BookCreateResponseDto;
import com.bookstore.readingisgood.dto.response.BookUpdateStockValueResponseDto;
import com.bookstore.readingisgood.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class BookControllerTest {
    @Mock
    BookService bookService;
    @Mock
    Logger log;
    @InjectMocks
    BookController bookController;

    BookUpdateStockValueResponseDto bookUpdateStockValueResponseDto;
    BookCreateResponseDto bookCreateResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookUpdateStockValueResponseDto = new BookUpdateStockValueResponseDto();
        bookUpdateStockValueResponseDto.setStock(0);
        bookUpdateStockValueResponseDto.setId(1L);

        bookCreateResponseDto = new BookCreateResponseDto();
        bookCreateResponseDto.setId(1L);
        bookCreateResponseDto.setTitle("title");
        bookCreateResponseDto.setAuthor("author");
    }

    @Test
    void testCreateBook() {
        when(bookService.createBook(any())).thenReturn(new BookCreateResponseDto(Long.valueOf(1), "title", "author"));

        ResponseEntity<BookCreateResponseDto> result = bookController.createBook(new BookCreateRequestDto("title", "author", Double.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)));
        Assertions.assertEquals(bookCreateResponseDto, result.getBody());
    }

    @Test
    void testUpdateBookStockValue() {
        when(bookService.updateBookStockValue(anyLong(), anyInt())).thenReturn(new BookUpdateStockValueResponseDto(Long.valueOf(1), Integer.valueOf(0)));

        ResponseEntity<BookUpdateStockValueResponseDto> result = bookController.updateBookStockValue(Long.valueOf(1), Integer.valueOf(0));
        Assertions.assertEquals(bookUpdateStockValueResponseDto, result.getBody());
    }
}

