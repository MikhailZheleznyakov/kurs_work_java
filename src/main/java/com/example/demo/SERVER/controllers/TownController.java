package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.TownRepository;
import com.example.demo.SERVER.tables.Town;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/town")
public class TownController {
    private final TownRepository townRepository;

    public TownController(TownRepository townRepository){
        this.townRepository = townRepository;
    }

    @PostMapping("/addTown")
    Town addTown(@RequestBody Town town){
        System.out.println(town);
        return this.townRepository.save(town);
    }

    @GetMapping("/getAllTown")
    List<Town> getTownAll(){
        return this.townRepository.findAll();
    }

    @DeleteMapping("/deleteTown/{id}")
    public ResponseEntity<?> deleteTown(@PathVariable Long id){
        return townRepository.findById(id)
                .map(town -> {
                    townRepository.delete(town);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));

    }

    @PutMapping("/updateTown/{id}")
    Town updateTown(@PathVariable Long id, @RequestBody Town townUpdate){
        return townRepository.findById(id)
                .map(town -> {
                    town.setName(townUpdate.getName());
                    town.setInfo(townUpdate.getInfo());
                    return townRepository.save(town);
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }
}
