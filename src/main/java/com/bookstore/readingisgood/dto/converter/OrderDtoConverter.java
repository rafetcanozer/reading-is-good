package com.bookstore.readingisgood.dto.converter;

import com.bookstore.readingisgood.dto.response.OrderCreateResponseDto;
import com.bookstore.readingisgood.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoConverter {

    public OrderCreateResponseDto fromOrderToOrderCreateResponseDto(Order order){
        return OrderCreateResponseDto.builder()
                .id(order.getId())
                .books(order.getBooks())
                .createTime(order.getCreateTime())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .totalBooks(order.getTotalBooks())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
