package com.bookstore.readingisgood.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Long customerId;

    private Integer totalBooks;

    private Double totalPrice;

    private String status;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<BookOrderRequest> books = new ArrayList<>();

    private LocalDateTime createTime = LocalDateTime.now();
}
