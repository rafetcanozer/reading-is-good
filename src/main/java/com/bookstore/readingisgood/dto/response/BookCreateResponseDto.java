package com.bookstore.readingisgood.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookCreateResponseDto {

    private Long id;

    private String title;

    private String author;

}
