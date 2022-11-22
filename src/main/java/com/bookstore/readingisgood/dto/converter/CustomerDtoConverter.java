package com.bookstore.readingisgood.dto.converter;

import com.bookstore.readingisgood.dto.request.CustomerCreateRequestDto;
import com.bookstore.readingisgood.dto.response.CustomerCreateResponseDto;
import com.bookstore.readingisgood.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    public Customer fromCustomerCreateRequestDtoToCustomer(CustomerCreateRequestDto customerCreateRequestDto){
        Customer customer = new Customer();
        customer.setEmail(customerCreateRequestDto.getEmail());
        customer.setAddress(customerCreateRequestDto.getAddress());
        customer.setPhone(customerCreateRequestDto.getPhone());
        customer.setCreateTime(customerCreateRequestDto.getCreateTime());
        customer.setFirstName(customerCreateRequestDto.getFirstName());
        customer.setLastName(customerCreateRequestDto.getLastName());
        customer.setDateOfBirth(customerCreateRequestDto.getDateOfBirth());
        return customer;
    }


    public CustomerCreateResponseDto fromCustomerToCustomerCreateResponseDto(Customer customer){
        return CustomerCreateResponseDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .createTime(customer.getCreateTime())
                .address(customer.getAddress())
                .dateOfBirth(customer.getDateOfBirth())
                .build();
    }

}
