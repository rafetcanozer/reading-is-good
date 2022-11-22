package com.bookstore.readingisgood.dto.request;

import com.bookstore.readingisgood.entity.BookOrderRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateRequestDto {

    private Long customerId;

    private List<BookOrderRequest> books;

}
