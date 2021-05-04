package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Cargo findCargoById(Long id);

    Cargo deleteCargoById(Long id);

    Optional<Cargo> findById(Long id);
}
