package com.bookstore.readingisgood.controller;

import com.bookstore.readingisgood.dto.request.CustomerCreateRequestDto;
import com.bookstore.readingisgood.dto.response.CustomerCreateResponseDto;
import com.bookstore.readingisgood.dto.response.OrderDto;
import com.bookstore.readingisgood.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerCreateResponseDto> createCustomer(@RequestBody CustomerCreateRequestDto customerCreateRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createNewCustomer(customerCreateRequestDto));
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<OrderDto>> listCustomerOrders(@PathVariable Long customerId, Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.listCustomerOrders(customerId,pageable));
    }

}
