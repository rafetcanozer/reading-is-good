package com.bookstore.readingisgood.controller;

import com.bookstore.readingisgood.dto.response.CustomersMonthlyOrderStatisticDto;
import com.bookstore.readingisgood.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CustomersMonthlyOrderStatisticDto>> listCustomerMonthlyOrderStatistic(@PathVariable Long customerId){
        return new ResponseEntity<>(statisticsService.listCustomersMonthlyOrderStatistic(customerId), HttpStatus.OK);
    }

}
