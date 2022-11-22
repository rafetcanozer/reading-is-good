package com.bookstore.readingisgood.service.impl;

import com.bookstore.readingisgood.dto.converter.OrderDtoConverter;
import com.bookstore.readingisgood.entity.BookOrderRequest;
import com.bookstore.readingisgood.dto.request.OrderCreateRequestDto;
import com.bookstore.readingisgood.dto.response.OrderCreateResponseDto;
import com.bookstore.readingisgood.dto.response.OrderDto;
import com.bookstore.readingisgood.entity.Book;
import com.bookstore.readingisgood.entity.Order;
import com.bookstore.readingisgood.exception.OrderNotFoundException;
import com.bookstore.readingisgood.exception.WrongDataException;
import com.bookstore.readingisgood.repository.OrderRepository;
import com.bookstore.readingisgood.service.BookService;
import com.bookstore.readingisgood.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;

    private final OrderDtoConverter orderDtoConverter;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto){

        Long customerId = orderCreateRequestDto.getCustomerId();
        List<BookOrderRequest> bookOrderList = orderCreateRequestDto.getBooks();

        final List<BookOrderRequest> invalidBookOrders = bookOrderList.stream()
                .filter(bookOrder -> bookOrder.getCount() <= 0)
                .collect(Collectors.toList());

        if (customerId == null || !invalidBookOrders.isEmpty()) {
            log.error("Create order request failed due to wrong customerId={} bookOrders={}", customerId, invalidBookOrders);
            throw new WrongDataException("Create order request failed due to invalid customerId, bookOrders");
        }

        List<Book> bookList = bookOrderList.stream()
                .map(bookOrder -> bookService.getBookHavingEnoughStock(bookOrder.getBookId(), bookOrder.getCount()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (bookList.size() < bookOrderList.size()) {
            log.error("Order is not created due to lack of requested books");
            throw new WrongDataException("Order is not created due to lack of requested books");
        }

        Map<Long, Integer> collect = bookOrderList.stream()
                .collect(Collectors.toMap(BookOrderRequest::getBookId, BookOrderRequest::getCount));

        bookList.forEach(book -> book.setStock(book.getStock() - collect.get(book.getId())));
        bookService.saveBooks(bookList);

        Double totalPrice = bookList.stream()
                .map(book -> collect.get(book.getId()) * book.getPrice())
                .reduce(0.0, Double::sum);

        Integer totalBooks = collect.values().stream()
                .reduce(0, Integer::sum);

        Order order = new Order();
        order.setCustomerId(customerId);
        order.getBooks().addAll(bookOrderList);
        order.setTotalBooks(totalBooks);
        order.setTotalPrice(totalPrice);
        order.setStatus("COMPLETE");


        Order savedOrder = orderRepository.save(order);

        return orderDtoConverter.fromOrderToOrderCreateResponseDto(savedOrder);

    }

    @Override
    public OrderDto getOrderById(Long orderId){
        Optional<Order> findOrder = orderRepository.findById(orderId);
        if(findOrder.isEmpty()){
            log.error("Order Not Found");
            throw new OrderNotFoundException("Order Not Found For Given Id: "+ orderId);
        }
        return OrderDto.fromOrder(findOrder.get());
    }

    @Override
    public List<OrderDto> listOrdersByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findByCreateTimeBetween(startDate, endDate);

        return orders.stream()
                .map(OrderDto::fromOrder)
                .collect(Collectors.toList());
    }



    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId, Pageable pageable) {

        Page<Order> pageOrders = orderRepository.findByCustomerId(customerId, pageable);
        List<Order> customerOrders = pageOrders.getContent();

        return customerOrders.stream()
                .map(OrderDto::fromOrder)
                .collect(Collectors.toList());
    }

}
