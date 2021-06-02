package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends JpaRepository of Driver, Long
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findDriverById(Long id);


    Driver deleteDriverById(Long id);

}
