package com.bookstore.readingisgood.service;

import com.bookstore.readingisgood.dto.request.OrderCreateRequestDto;
import com.bookstore.readingisgood.dto.response.OrderCreateResponseDto;
import com.bookstore.readingisgood.dto.response.OrderDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);

    OrderDto getOrderById(Long orderId);

    List<OrderDto> listOrdersByDate(LocalDateTime startDate, LocalDateTime endDate);

    List<OrderDto> getOrdersByCustomerId(Long customerId, Pageable pageable);
}
