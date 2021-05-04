package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Driver;
import com.example.demo.SERVER.tables.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    Transport findTransportById(Long id);

    Transport findTransportByName(String name);

    Transport findTransportByCapacity(Long capacity);

    Transport deleteTransportById(Long id);
}
