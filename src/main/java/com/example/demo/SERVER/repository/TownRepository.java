package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
    Town findTownById(Long id);

    Town deleteTownById(Long id);
}
