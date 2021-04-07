package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.OrderRepository;
import com.example.demo.SERVER.tables.Order;
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

    @GetMapping("/getOrder")
    Order getOrder(@PathVariable Long id){
        return this.orderRepository.findOrderById(id);
    }

    @GetMapping("/getOrderAll")
    List<Order> getOrderAll(){
        return this.orderRepository.findAll();
    }

    @DeleteMapping("/deleteOrder={id}")
    Order deleteOrder(@PathVariable Long id){
        return this.orderRepository.deleteOrderById(id);
    }

    @PutMapping("/updateOrder={id}")
    Order updateOrder(@PathVariable Long id, @RequestBody Order orderUpdate){
        Order order = orderRepository.findOrderById(id);
        order.setId(orderUpdate.getId());
        order.setClient(orderUpdate.getClient());
        order.setInfo(orderUpdate.getInfo());
        return this.orderRepository.save(order);
    }

}
