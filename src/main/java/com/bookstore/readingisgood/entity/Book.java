package com.bookstore.readingisgood.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Book")
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    private String author;

    @DecimalMin("0.01")
    private Double price;

    @Min(1500)
    private Integer publishYear;

    @Min(0)
    private Integer stock;

    private LocalDateTime createDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

}
