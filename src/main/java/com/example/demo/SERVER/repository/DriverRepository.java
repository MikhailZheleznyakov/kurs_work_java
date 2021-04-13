package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    public Driver findDriverById(Long id);


    public Driver deleteDriverById(Long id);
}
