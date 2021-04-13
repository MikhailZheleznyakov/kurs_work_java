package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportTypeRepository extends JpaRepository<TransportType, Long> {
    public TransportType findTransportTypeById(Long id);

    public TransportType deleteTransportTypeById(Long id);
}
