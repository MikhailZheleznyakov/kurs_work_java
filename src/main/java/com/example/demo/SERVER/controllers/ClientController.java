package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.ClientRepository;
import com.example.demo.SERVER.tables.Client;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Client controller
 */
@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @PostMapping("/addClient")
    Client createClient(@RequestBody Client client){
        System.out.println(client);
        return this.clientRepository.save(client);
    }

    @GetMapping("/getAllClient")
    List<Client> getClientAll(){
        return this.clientRepository.findAll();
    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){
        return clientRepository.findById(id)
                .map(cargo -> {
                    clientRepository.delete(cargo);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }

    @PutMapping("/updateClient/{id}")
    Client updateClient(@PathVariable Long id, @RequestBody Client clientUpdate) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setName(clientUpdate.getName());
                    client.setSurname(clientUpdate.getSurname());
                    client.setLogin(clientUpdate.getLogin());
                    client.setPhone(clientUpdate.getPhone());
                    return clientRepository.save(client);
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }
}
