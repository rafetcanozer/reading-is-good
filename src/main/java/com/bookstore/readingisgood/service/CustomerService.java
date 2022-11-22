package com.bookstore.readingisgood.service;

import com.bookstore.readingisgood.dto.request.CustomerCreateRequestDto;
import com.bookstore.readingisgood.dto.response.CustomerCreateResponseDto;
import com.bookstore.readingisgood.dto.response.OrderDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    CustomerCreateResponseDto createNewCustomer(CustomerCreateRequestDto customerCreateRequestDto);

    List<OrderDto> listCustomerOrders(Long customerId, Pageable pageable);
}
