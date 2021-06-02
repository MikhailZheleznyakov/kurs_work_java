package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.DriverRepository;
import com.example.demo.SERVER.tables.Driver;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Driver controller
 */
@RestController
@RequestMapping("/driver")
public class DriverController {
    private final DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    @PostMapping("/addDriver")
    Driver createDriver(@RequestBody Driver driver){
        System.out.println(driver);
        return this.driverRepository.save(driver);
    }

    @GetMapping("/getAllDriver")
    List<Driver> getDriverAll(){
        return this.driverRepository.findAll();
    }

    @DeleteMapping("/deleteDriver/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long id){
        return driverRepository.findById(id)
                .map(driver -> {
                    driverRepository.delete(driver);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }

    @PutMapping("/updateDriver/{id}")
    Driver updateDriver(@PathVariable Long id, @RequestBody Driver driverUpdate){
        return driverRepository.findById(id)
                .map(driver -> {
                    driver.setName(driverUpdate.getName());
                    driver.setSurname(driverUpdate.getSurname());
                    return driverRepository.save(driver);
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }

}
