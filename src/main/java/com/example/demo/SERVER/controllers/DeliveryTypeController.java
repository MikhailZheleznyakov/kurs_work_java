package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.DeliveryTypeRepository;
import com.example.demo.SERVER.tables.DeliveryType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliverytype")
public class DeliveryTypeController {
    private final DeliveryTypeRepository deliveryTypeRepository;

    public DeliveryTypeController(DeliveryTypeRepository deliveryTypeRepository){
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    @PostMapping("/adddeliverytype")
    DeliveryType createDeliveryType(@RequestBody DeliveryType deliveryType){
        System.out.println(deliveryType);
        return this.deliveryTypeRepository.save(deliveryType);
    }

    @GetMapping("/getDeliveryType={typeName}")
    DeliveryType getClient(@PathVariable String typeName){
        return this.deliveryTypeRepository.findDeliveryTypeByTypeName(typeName);
    }

    @GetMapping("/allClients")
    List<DeliveryType> getDeliveryTypeAll(){
        return this.deliveryTypeRepository.findAll();
    }

    @DeleteMapping("/deleteDeliveryType={typeName}")
    DeliveryType deleteDeliveryType(@PathVariable String typeName){
        return this.deliveryTypeRepository.deleteDeliveryTypeByTypeName(typeName);
    }

    @PutMapping("/updateDeliveryType={typeName}")
    DeliveryType updateDeliveryType(@PathVariable String typeName, @RequestBody DeliveryType deliveryTypeUpdate){
        DeliveryType deliveryType = deliveryTypeRepository.findDeliveryTypeByTypeName(typeName);
        deliveryType.setTypeName(deliveryTypeUpdate.getTypeName());
        deliveryType.setDescription(deliveryTypeUpdate.getDescription());
        return this.deliveryTypeRepository.save(deliveryType);
    }
}
