package com.bookstore.readingisgood.dto.response;

import com.bookstore.readingisgood.entity.BookOrderRequest;
import com.bookstore.readingisgood.entity.Order;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class OrderDto {

    private Long customerId;

    private List<BookOrderRequest> bookOrders;

    private Integer totalBooks;

    private LocalDateTime createTime;

    private Double totalPrice;

    private String status;

    public static OrderDto fromOrder(Order order) {

        return OrderDto.builder()
                .customerId(order.getCustomerId())
                .bookOrders(order.getBooks())
                .totalBooks(order.getTotalBooks())
                .createTime(order.getCreateTime())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }
}
