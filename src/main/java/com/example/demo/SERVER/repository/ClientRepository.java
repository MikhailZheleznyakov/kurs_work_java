package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client findClientById(Long id);

    public List<Client> findAll();

    public Client deleteClientById(Long id);

}
