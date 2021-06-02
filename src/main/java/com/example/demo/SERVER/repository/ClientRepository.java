package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends JpaRepository of CLient, Long
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    /**
     *
     * @param id
     * @return client object found
     */
    Client findClientById(Long id);

    /**
     *
     * @param id
     * @return client object deleted
     */
    Client deleteClientById(Long id);

    Client findClientByLogin(String s);



}
