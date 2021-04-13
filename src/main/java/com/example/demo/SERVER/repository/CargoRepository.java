package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    public Cargo findCargoById(Long id);

    public List<Cargo> findAll();

    public Cargo deleteCargoById(Long id);


}
