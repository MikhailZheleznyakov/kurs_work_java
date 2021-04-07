package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.ClientRepository;
import com.example.demo.SERVER.tables.Client;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @PostMapping("/addClient")
    Client createCargo(@RequestBody Client client){
        System.out.println(client);
        return this.clientRepository.save(client);
    }

    @GetMapping("/getClient={id}")
    Client getClient(@PathVariable Long id){
        return this.clientRepository.findClientById(id);
    }

    @GetMapping("/allClients")
    List<Client> getClientAll(){
        return this.clientRepository.findAll();
    }

    @DeleteMapping("/deleteClient={id}")
    Client deleteClient(@PathVariable Long id){
        return this.clientRepository.deleteClientById(id);
    }

    @PutMapping("/updateClient={id}")
    Client updateClient(@PathVariable Long id, @RequestBody Client clientUpdate) {
        Client client = clientRepository.findClientById(id);
        client.setId(clientUpdate.getId());
        client.setSurname(clientUpdate.getSurname());
        client.setName(clientUpdate.getName());
        client.setLogin(clientUpdate.getLogin());
        client.setPhone(clientUpdate.getPhone());
        return this.clientRepository.save(client);
    }
}
