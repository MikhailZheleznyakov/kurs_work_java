package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.RateRepository;
import com.example.demo.SERVER.repository.TownRepository;
import com.example.demo.SERVER.tables.Rate;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rate controller
 */
@RestController
@RequestMapping("/rate")
public class RateController {
    private final RateRepository rateRepository;
    private final TownRepository townRepository;

    public RateController(RateRepository rateRepository, TownRepository townRepository){
        this.rateRepository = rateRepository;
        this.townRepository = townRepository;
    }

    @PostMapping("/addRate")
    Rate addRate(@RequestBody Rate rate){
        System.out.println(rate);
        return this.rateRepository.save(rate);
    }

    @GetMapping("/getAllRate")
    List<Rate> getRateAll(){
        return this.rateRepository.findAll();
    }

    @DeleteMapping("/deleteRate/{id}")
    public ResponseEntity<?> deleteRate(@PathVariable Long id){
        return rateRepository.findById(id)
                .map(rate -> {
                    rateRepository.delete(rate);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }

    @PutMapping("/updateRate/{id}")
    public Rate updateRate(@PathVariable Long id, @RequestBody Rate rateUpdate) {
        return rateRepository.findById(id)
                .map(rate -> {
                    rate.setArrivaltown(rateUpdate.getArrivaltown());
                    rate.setDeparttown(rateUpdate.getDeparttown());
                    rate.setCost(rateUpdate.getCost());
                    return rateRepository.save(rate);
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }
}
