package com.bookstore.readingisgood.service.impl;

import com.bookstore.readingisgood.dto.converter.BookDtoConverter;
import com.bookstore.readingisgood.dto.request.BookCreateRequestDto;
import com.bookstore.readingisgood.dto.response.BookCreateResponseDto;
import com.bookstore.readingisgood.dto.response.BookUpdateStockValueResponseDto;
import com.bookstore.readingisgood.entity.Book;
import com.bookstore.readingisgood.exception.BookNotFoundException;
import com.bookstore.readingisgood.exception.ConflictException;
import com.bookstore.readingisgood.exception.WrongDataException;
import com.bookstore.readingisgood.repository.BookRepository;
import com.bookstore.readingisgood.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookDtoConverter bookDtoConverter;

    @Override
    public BookCreateResponseDto createBook(BookCreateRequestDto bookCreateRequestDto){

        log.info("-----Book Create Method Begin-----");

        String bookTitle = bookCreateRequestDto.getTitle();
        String bookAuthor = bookCreateRequestDto.getAuthor();
        Integer bookPublishYear = bookCreateRequestDto.getPublishYear();
        Double bookPrice = bookCreateRequestDto.getPrice();

        if(bookAuthor == null || bookPrice==null || bookTitle==null){
            log.error("Book not create with author={} or price={} or title={}", bookAuthor, bookPrice,bookTitle);
            throw new WrongDataException("Book not create with empty author, price, title value");
        }

        if(bookPrice<0){
            throw new WrongDataException("Book price must be greater than zero");
        }

        if (bookPublishYear<1500){
            throw new WrongDataException("Book Publish Year must be greater than 1500");
        }
        if (bookCreateRequestDto.getStock()<0){
            throw new WrongDataException("Book Stock Must Be Greater Than 0");
        }

        Optional<Book> bookExist = bookRepository.findByTitleAndAuthorAndPublishYear(bookTitle,bookAuthor,bookPublishYear);

        if (bookExist.isPresent()){
            log.info("Book already exist with this title={},bookAuthor={},publishyear={}",bookTitle,bookAuthor,bookPublishYear);
            throw new ConflictException("Book already exist with this title, bookAuthor and publishyear");
        }

        Book saveBook = bookRepository.saveAndFlush(bookDtoConverter.fromBookCreateRequestDtoToBook(bookCreateRequestDto));
        log.info("Book saved.");
        return bookDtoConverter.fromBookToBookCreateResponseDto(saveBook);
    }

    @Override
    public BookUpdateStockValueResponseDto updateBookStockValue(Long bookId, Integer stock){

        log.info("-----Book Update Stock Value Method Begin-----");

        Optional<Book> currentBook = bookRepository.findById(bookId);

        if (stock<0){
            throw new WrongDataException("Stock Must Be Greater Than 0");
        }

        if (currentBook.isEmpty()){
            throw new BookNotFoundException("Book not found given id: " + bookId);
        }

        Book updateBook = currentBook.get();
        updateBook.setStock(stock);
        updateBook.setUpdatedDate(LocalDateTime.now());
        Book updatedBook = bookRepository.save(updateBook);
        log.info("Book updated id={} and stockValue={} ",updatedBook.getId(),updatedBook.getStock());

        return bookDtoConverter.fromBookToBookUpdateStockValueResponseDto(updatedBook);

    }

    @Override
    public Book getBookHavingEnoughStock(Long bookId, Integer stock) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (bookOpt.isEmpty()) {
            log.error("Book is not found with bookId={}", bookId);
            return null;
        } else if (bookOpt.get().getStock() < stock) {
            log.error("Book does not have enough stock with bookId={} existingStock={} requestedStock={}",
                    bookId, bookOpt.get().getStock(), stock);
            return null;
        }
        return bookOpt.get();
    }

    @Override
    public void saveBooks(List<Book> bookList) {
        for (Book book : bookList) {
            book.setUpdatedDate(LocalDateTime.now());
            bookRepository.save(book);
        }
    }


}
