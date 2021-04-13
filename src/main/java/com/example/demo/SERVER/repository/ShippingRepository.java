package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    Shipping findShippingById(Long id);

    Shipping deleteShippingById(Long id);
}
