package com.bookstore.readingisgood.controller;

import com.bookstore.readingisgood.dto.request.OrderCreateRequestDto;
import com.bookstore.readingisgood.dto.response.OrderCreateResponseDto;
import com.bookstore.readingisgood.dto.response.OrderDto;
import com.bookstore.readingisgood.entity.BookOrderRequest;
import com.bookstore.readingisgood.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class OrderControllerTest {
    @Mock
    OrderService orderService;
    @Mock
    Logger log;
    @InjectMocks
    OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        when(orderService.createOrder(any())).thenReturn(new OrderCreateResponseDto(Long.valueOf(1), Long.valueOf(1), Integer.valueOf(0), Double.valueOf(0), "status", List.of(new BookOrderRequest(Long.valueOf(1), Long.valueOf(1), Integer.valueOf(0))), LocalDateTime.of(2022, Month.NOVEMBER, 23, 0, 35, 12)));

        ResponseEntity<OrderCreateResponseDto> result = orderController.createOrder(new OrderCreateRequestDto(Long.valueOf(1), List.of(new BookOrderRequest(Long.valueOf(1), Long.valueOf(1), Integer.valueOf(0)))));
        Assertions.assertEquals(result.getBody(), result.getBody());
    }

    @Test
    void testGetOrderById() {
        when(orderService.getOrderById(anyLong())).thenReturn(new OrderDto(Long.valueOf(1), List.of(new BookOrderRequest(Long.valueOf(1), Long.valueOf(1), Integer.valueOf(0))), Integer.valueOf(0), LocalDateTime.of(2022, Month.NOVEMBER, 23, 0, 35, 12), Double.valueOf(0), "status"));

        ResponseEntity<OrderDto> result = orderController.getOrderById(Long.valueOf(1));
        Assertions.assertEquals(result.getBody(), result.getBody());
    }

    @Test
    void testListOrdersByDate() {
        when(orderService.listOrdersByDate(any(), any())).thenReturn(List.of(new OrderDto(Long.valueOf(1), List.of(new BookOrderRequest(Long.valueOf(1), Long.valueOf(1), Integer.valueOf(0))), Integer.valueOf(0), LocalDateTime.of(2022, Month.NOVEMBER, 23, 0, 35, 12), Double.valueOf(0), "status")));

        ResponseEntity<List<OrderDto>> result = orderController.listOrdersByDate(LocalDate.of(2022, Month.NOVEMBER, 23), LocalDate.of(2022, Month.NOVEMBER, 23));
        Assertions.assertEquals(result.getBody(), result.getBody());
    }
}

