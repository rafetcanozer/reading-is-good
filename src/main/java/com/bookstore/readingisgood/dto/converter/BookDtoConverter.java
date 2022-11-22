package com.bookstore.readingisgood.dto.converter;

import com.bookstore.readingisgood.dto.request.BookCreateRequestDto;
import com.bookstore.readingisgood.dto.response.BookCreateResponseDto;
import com.bookstore.readingisgood.dto.response.BookUpdateStockValueResponseDto;
import com.bookstore.readingisgood.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter {

    public Book fromBookCreateRequestDtoToBook(BookCreateRequestDto bookCreateRequestDto){
        Book book = new Book();
        book.setTitle(bookCreateRequestDto.getTitle());
        book.setPrice(bookCreateRequestDto.getPrice());
        book.setAuthor(bookCreateRequestDto.getAuthor());
        book.setStock(bookCreateRequestDto.getStock());
        book.setPublishYear(bookCreateRequestDto.getPublishYear());
        return book;
    }

    public BookCreateResponseDto fromBookToBookCreateResponseDto(Book book){
        return BookCreateResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }

    public BookUpdateStockValueResponseDto fromBookToBookUpdateStockValueResponseDto(Book book){
        return BookUpdateStockValueResponseDto.builder()
                .id(book.getId())
                .stock(book.getStock())
                .build();
    }

}
