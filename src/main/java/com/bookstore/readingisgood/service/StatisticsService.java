package com.bookstore.readingisgood.service;

import com.bookstore.readingisgood.dto.response.CustomersMonthlyOrderStatisticDto;

import java.util.List;

public interface StatisticsService {
    List<CustomersMonthlyOrderStatisticDto> listCustomersMonthlyOrderStatistic(Long customerId);
}
