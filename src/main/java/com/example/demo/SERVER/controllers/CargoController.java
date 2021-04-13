package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.CargoRepository;
import com.example.demo.SERVER.tables.Cargo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoController {
    private final CargoRepository cargoRepository;

    public CargoController(CargoRepository cargoRepository){
        this.cargoRepository = cargoRepository;
    }

    @PostMapping("/addCargo")
    Cargo createCargo(@RequestBody Cargo cargo) {
        System.out.println(cargo);
        return this.cargoRepository.save(cargo);
    }

    @GetMapping("/getCargo/{id}")
    Cargo getCargo(@PathVariable Long id){
        return this.cargoRepository.findCargoById(id);
    }

    @GetMapping("/allCargo")
    List<Cargo> getCargoAll(){
        return this.cargoRepository.findAll();
    }

    @DeleteMapping("/deleteCargo={id}")
    Cargo deleteCargo(@PathVariable Long id){
        return this.cargoRepository.deleteCargoById(id);
    }

    @PutMapping("/putCargo={id}")
    Cargo updateCargo(@PathVariable Long id, @RequestBody Cargo cargoUpdate) {
        Cargo cargo = cargoRepository.findCargoById(id);
        cargo.setId(cargoUpdate.getId());
        cargo.setName(cargoUpdate.getName());
        cargo.setOrder(cargoUpdate.getOrder());
        cargo.setWeight(cargoUpdate.getWeight());
        return cargoRepository.save(cargo);
    }

}
