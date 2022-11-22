package com.bookstore.readingisgood.controller;

import com.bookstore.readingisgood.dto.request.OrderCreateRequestDto;
import com.bookstore.readingisgood.dto.response.OrderCreateResponseDto;
import com.bookstore.readingisgood.dto.response.OrderDto;
import com.bookstore.readingisgood.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreateResponseDto> createOrder(@RequestBody OrderCreateRequestDto orderCreateRequestDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderCreateRequestDto));
        } catch (OptimisticLockingFailureException ex) {
            log.error("Concurrent update is detected. Please try again!!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId){
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> listOrdersByDate(@DateTimeFormat(iso = DATE) @RequestParam LocalDate startDate,
                                                                 @DateTimeFormat(iso = DATE) @RequestParam LocalDate endDate){
            return new ResponseEntity<>(orderService.listOrdersByDate(startDate.atStartOfDay(),endDate.atStartOfDay()),HttpStatus.OK);
    }
}
