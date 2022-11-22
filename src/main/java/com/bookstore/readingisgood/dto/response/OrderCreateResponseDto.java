package com.bookstore.readingisgood.dto.response;

import com.bookstore.readingisgood.entity.BookOrderRequest;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderCreateResponseDto {

    private Long id;

    private Long customerId;

    private Integer totalBooks;

    private Double totalPrice;

    private String status;

    private List<BookOrderRequest> books;

    private LocalDateTime createTime;



}
