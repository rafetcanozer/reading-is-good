package com.bookstore.readingisgood.repository;

import com.bookstore.readingisgood.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

    List<Order> findByCreateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);


}
