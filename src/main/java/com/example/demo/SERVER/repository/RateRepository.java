package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, String> {

    Optional<Rate> findById(Long id);
}
