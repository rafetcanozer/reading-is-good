package com.bookstore.readingisgood.service.impl;

import com.bookstore.readingisgood.dto.response.CustomersMonthlyOrderStatisticDto;
import com.bookstore.readingisgood.dto.response.OrderDto;
import com.bookstore.readingisgood.service.CustomerService;
import com.bookstore.readingisgood.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final CustomerService customerService;

    @Override
    public List<CustomersMonthlyOrderStatisticDto> listCustomersMonthlyOrderStatistic(Long customerId){

        List<OrderDto> customerOrders = customerService.listCustomerOrders(customerId, Pageable.unpaged());

        Map<Month, List<OrderDto>> monthlyOrderListMap = customerOrders.stream()
                .collect(Collectors.groupingBy(order -> order.getCreateTime().getMonth()));

        return monthlyOrderListMap.entrySet().stream()
                .map(entry -> createCustomerMonthlyStatistics(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    private CustomersMonthlyOrderStatisticDto createCustomerMonthlyStatistics(Month month, List<OrderDto> orderDtoList){
        int totalBookCounter =0;
        double totalPurchasedAmountCounter=0;

        for (OrderDto order : orderDtoList){
            totalBookCounter += order.getTotalBooks();
            totalPurchasedAmountCounter += order.getTotalPrice();
        }
        return new CustomersMonthlyOrderStatisticDto(month.toString(), orderDtoList.size(), totalBookCounter, totalPurchasedAmountCounter);
    }

}
