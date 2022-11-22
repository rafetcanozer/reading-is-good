package com.bookstore.readingisgood.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CustomersMonthlyOrderStatisticDto {

    private String month;

    private Integer totalOrderCount;

    private Integer totalBookCount;

    private Double totalPurchasedAmount;

}
