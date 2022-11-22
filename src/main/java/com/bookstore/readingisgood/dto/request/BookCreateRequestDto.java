package com.bookstore.readingisgood.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequestDto {


    private String title;

    private String author;

    private Double price;

    private Integer publishYear;

    private Integer stock;

}
