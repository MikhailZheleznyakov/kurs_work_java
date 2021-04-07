package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);

    Order deleteOrderById(Long id);
}
