package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);

    List<Order> findAll();

    Order deleteOrderById(Long id);
}
