package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.DriverRepository;
import com.example.demo.SERVER.repository.TransportRepository;
import com.example.demo.SERVER.tables.Driver;
import com.example.demo.SERVER.tables.Transport;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Transport controller
 */
@RestController
@RequestMapping("/transport")
public class TransportController {
    private final TransportRepository transportRepository;
    private final DriverRepository driverRepository;

    public TransportController(TransportRepository transportRepository, DriverRepository driverRepository){
        this.transportRepository = transportRepository;
        this.driverRepository = driverRepository;
    }

    @PostMapping("/addTransport")
    Transport addTransport(@RequestBody Transport transport){
        return this.transportRepository.save(transport);
    }

    @GetMapping("/getAllTransport")
    List<Transport> getTransportAll(){
        return this.transportRepository.findAll();
    }

    @DeleteMapping("/deleteTransport/{id}")
    public ResponseEntity<?> deleteTransport(@PathVariable Long id){
        return transportRepository.findById(id)
                .map(transport -> {
                    transportRepository.delete(transport);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));

    }

    @PutMapping("/updateTransport/{id}")
    Transport updateTransport(@PathVariable Long id, @RequestBody Transport transportUpdate){
        return transportRepository.findById(id)
                .map(transport -> {
                    transport.setName(transportUpdate.getName());
                    transport.setCapacity(transportUpdate.getCapacity());
                    transport.setWearout(transportUpdate.getWearout());
                    transport.setDriver(transportUpdate.getDriver());
                    transport.setTransport_type(transportUpdate.getTransport_type());

                    return transportRepository.save(transport);
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }
}
