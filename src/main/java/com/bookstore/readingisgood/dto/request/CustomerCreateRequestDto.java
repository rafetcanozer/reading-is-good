package com.bookstore.readingisgood.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCreateRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private Integer dateOfBirth;

    private LocalDateTime createTime;

}
