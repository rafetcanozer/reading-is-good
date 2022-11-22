package com.bookstore.readingisgood.controller;

import com.bookstore.readingisgood.dto.request.BookCreateRequestDto;
import com.bookstore.readingisgood.dto.response.BookCreateResponseDto;
import com.bookstore.readingisgood.dto.response.BookUpdateStockValueResponseDto;
import com.bookstore.readingisgood.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookCreateResponseDto> createBook(@RequestBody BookCreateRequestDto bookCreateRequestDto){
        log.info("Create Book Request Received.");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookCreateRequestDto));
    }

    @PatchMapping("{bookId}")
    public ResponseEntity<BookUpdateStockValueResponseDto> updateBookStockValue(@PathVariable Long bookId, @RequestParam Integer stock){
        log.info("Update Book Stock Value Request Received.");
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBookStockValue(bookId,stock));
    }
}
