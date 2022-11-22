package com.bookstore.readingisgood.controller;

import com.bookstore.readingisgood.dto.request.CustomerCreateRequestDto;
import com.bookstore.readingisgood.dto.response.CustomerCreateResponseDto;
import com.bookstore.readingisgood.dto.response.OrderDto;
import com.bookstore.readingisgood.entity.BookOrderRequest;
import com.bookstore.readingisgood.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class CustomerControllerTest {
    @Mock
    CustomerService customerService;
    @Mock
    Logger log;
    @InjectMocks
    CustomerController customerController;

    CustomerCreateResponseDto customerCreateResponseDto;
    List<OrderDto> orderDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerCreateResponseDto = new CustomerCreateResponseDto(Long.valueOf(1), "firstName", "lastName", "email", "phone", "address", Integer.valueOf(0), LocalDateTime.of(2022, Month.NOVEMBER, 23, 0, 24, 13));

    }

    @Test
    void testCreateCustomer() {
        when(customerService.createNewCustomer(any())).thenReturn(new CustomerCreateResponseDto(Long.valueOf(1), "firstName", "lastName", "email", "phone", "address", Integer.valueOf(0), LocalDateTime.of(2022, Month.NOVEMBER, 23, 0, 24, 13)));

        ResponseEntity<CustomerCreateResponseDto> result = customerController.createCustomer(new CustomerCreateRequestDto("firstName", "lastName", "email", "phone", "address", Integer.valueOf(0), LocalDateTime.of(2022, Month.NOVEMBER, 23, 0, 24, 13)));
        Assertions.assertEquals(customerCreateResponseDto, result.getBody());
    }

    @Test
    void testListCustomerOrders() {
        when(customerService.listCustomerOrders(anyLong(), any())).thenReturn(List.of(new OrderDto(Long.valueOf(1), List.of(new BookOrderRequest(Long.valueOf(1), Long.valueOf(1), Integer.valueOf(0))), Integer.valueOf(0), LocalDateTime.of(2022, Month.NOVEMBER, 23, 0, 24, 13), Double.valueOf(0), "status")));

        ResponseEntity<List<OrderDto>> result = customerController.listCustomerOrders(Long.valueOf(1), null);
        Assertions.assertEquals(result.getBody(), result.getBody());
    }
}

