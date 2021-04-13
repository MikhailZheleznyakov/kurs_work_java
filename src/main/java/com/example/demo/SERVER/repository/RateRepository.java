package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, String> {
    public Rate findRateByArrival_town(String arrival_town);

    public Rate findRateByDepart_town(String depart_town);

    public Rate deleteRateByArrival_town(String arrival_town);
}
