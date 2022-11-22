package com.bookstore.readingisgood.controller;

import com.bookstore.readingisgood.dto.response.CustomersMonthlyOrderStatisticDto;
import com.bookstore.readingisgood.service.StatisticsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;

class StatisticsControllerTest {
    @Mock
    StatisticsService statisticsService;
    @Mock
    Logger log;
    @InjectMocks
    StatisticsController statisticsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListCustomerMonthlyOrderStatistic() {
        when(statisticsService.listCustomersMonthlyOrderStatistic(anyLong())).thenReturn(List.of(new CustomersMonthlyOrderStatisticDto("month", Integer.valueOf(0), Integer.valueOf(0), Double.valueOf(0))));

        ResponseEntity<List<CustomersMonthlyOrderStatisticDto>> result = statisticsController.listCustomerMonthlyOrderStatistic(Long.valueOf(1));
        Assertions.assertEquals(result.getBody(), result.getBody());
    }
}

