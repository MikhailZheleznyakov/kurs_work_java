package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    public Transport findTransportById(Long id);

    public Transport findTransportByName(String name);

    public Transport findTransportByCapacity(Long capacity);

    public Transport deleteTransportById(Long id);
}
