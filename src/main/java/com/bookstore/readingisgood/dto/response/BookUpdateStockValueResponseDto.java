package com.bookstore.readingisgood.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookUpdateStockValueResponseDto {
    private Long id;
    private Integer stock;
}
