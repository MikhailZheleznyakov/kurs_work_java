package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, Long> {
    public DeliveryType findDeliveryTypeByTypeName(String typeName);

    public DeliveryType deleteDeliveryTypeByTypeName(String typeName);
}
