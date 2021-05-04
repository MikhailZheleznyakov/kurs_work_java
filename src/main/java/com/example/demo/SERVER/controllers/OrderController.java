package com.example.demo.SERVER.controllers;


import com.example.demo.SERVER.repository.OrderRepository;
import com.example.demo.SERVER.tables.Order;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @PostMapping("/addOrder")
    Order addOrder(@RequestBody Order order){
        System.out.println(order);
        return this.orderRepository.save(order);
    }

    @GetMapping("/getOrder/{id}")
    Order getOrder(@PathVariable Long id){
        return this.orderRepository.findOrderById(id);
    }

    @GetMapping("/getAllOrder")
    List<Order> getOrderAll(){
        return this.orderRepository.findAll();
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    orderRepository.delete(order);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("not found" + id));
    }

    @PutMapping("/updateOrder/{id}")
    Order updateOrder(@PathVariable Long id, @RequestBody Order orderUpdate){
        return orderRepository.findById(id)
                .map(order -> {
                    order.setClient_id(orderUpdate.getClient_id());
                    order.setArrivaltown(orderUpdate.getArrivaltown());
                    order.setDeparttown(orderUpdate.getDeparttown());
                    order.setDelivery_type(orderUpdate.getDelivery_type());
                    order.setTransport(orderUpdate.getTransport());
                    order.setCost(orderUpdate.getCost());
                    return orderRepository.save(order);
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }

}
