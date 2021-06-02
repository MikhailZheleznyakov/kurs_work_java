package com.example.demo.SERVER.controllers;


import com.example.demo.SERVER.repository.CargoRepository;
import com.example.demo.SERVER.tables.Cargo;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoController {
    private final CargoRepository cargoRepository;

    /**
     *
     * @param cargoRepository
     */
    public CargoController(CargoRepository cargoRepository){
        this.cargoRepository = cargoRepository;
    }

    /**
     *
     * @param cargo
     * @return created cargo
     */
    @PostMapping("/addCargo")
    Cargo createCargo(@RequestBody Cargo cargo) {
        System.out.println(cargo.toString());
        return this.cargoRepository.save(cargo);
    }

    @GetMapping("/getAllCargo")
    List<Cargo> getCargoAll(){
        return this.cargoRepository.findAll();
    }

    @DeleteMapping("/deleteCargo/{id}")
    public ResponseEntity<?> deleteCargo(@PathVariable Long id){
        return cargoRepository.findById(id)
                .map(cargo -> {
                    cargoRepository.delete(cargo);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));

    }

    @PutMapping("/updateCargo/{id}")
    public Cargo updateCargo(@PathVariable Long id, @RequestBody Cargo cargoUpdate) {
        return cargoRepository.findById(id)
                .map(cargo -> {
                    cargo.setName(cargoUpdate.getName());
                    cargo.setWeight(cargoUpdate.getWeight());
                    cargo.setOrder(cargoUpdate.getOrder());
                    return cargoRepository.save(cargo);
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }
}
