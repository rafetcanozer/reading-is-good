package com.bookstore.readingisgood.service.impl;

import com.bookstore.readingisgood.dto.request.CustomerCreateRequestDto;
import com.bookstore.readingisgood.dto.converter.CustomerDtoConverter;
import com.bookstore.readingisgood.dto.response.CustomerCreateResponseDto;
import com.bookstore.readingisgood.dto.response.OrderDto;
import com.bookstore.readingisgood.entity.Customer;
import com.bookstore.readingisgood.exception.ConflictException;
import com.bookstore.readingisgood.exception.WrongDataException;
import com.bookstore.readingisgood.repository.CustomerRepository;
import com.bookstore.readingisgood.service.CustomerService;
import com.bookstore.readingisgood.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    private final OrderService orderService;

    @Override
    public CustomerCreateResponseDto createNewCustomer(CustomerCreateRequestDto customerCreateRequestDto){
        log.info("-----createNewCustomer Method Begin-----");

        String email = customerCreateRequestDto.getEmail();
        String phone = customerCreateRequestDto.getPhone();

        Optional<Customer> foundCustomerByPhoneOrEmail = customerRepository.findByEmailOrPhone(email,phone);

        boolean emailValid = EmailValidator.getInstance().isValid(email);

        if(!emailValid){
            log.info("E-mail address not valid for : " + email);
            throw new WrongDataException("Email address not valid for : "+email);
        }

        if(foundCustomerByPhoneOrEmail.isPresent()){
            log.info("Customer already exist with this phone={} or email={}",phone,email);
            throw new ConflictException("Customer is already exist with email/phone");
        }

        log.info("Save Customer.");
        Customer customer = customerRepository.saveAndFlush(Objects.requireNonNull(customerDtoConverter.fromCustomerCreateRequestDtoToCustomer(customerCreateRequestDto)));

        return customerDtoConverter.fromCustomerToCustomerCreateResponseDto(customer);
    }

    @Override
    public List<OrderDto> listCustomerOrders(Long customerId, Pageable pageable){
        log.info("-----listCustomerOrders Method Begin-----");
        return orderService.getOrdersByCustomerId(customerId,pageable);
    }
}
