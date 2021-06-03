package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface that extends JpaRepository of Cargo, Long
 */
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    /**
     *
     * @param id Long
     * @return cargo object found
     */
    Cargo findCargoById(Long id);

    /**
     *
     * @param id Long
     * @return cargo object deleted
     */
    Cargo deleteCargoById(Long id);

    /**
     *
     * @param id Long
     * @return cargo object
     */
    Optional<Cargo> findById(Long id);

    Cargo findCargoByName(String  name);
}
