package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.*;
import com.example.demo.SERVER.tables.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CargoControllerTest {
    @Autowired
    public MockMvc mvc;
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public TownRepository townRepository;
    @Autowired
    public TransportRepository transportRepository;
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public DriverRepository driverRepository;

    @Test
    void createCargo() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "пиво");
            jsonObject.put("weight", 2000);
            JSONObject jsonOrder = new JSONObject();
            Order order = new Order();
            order = orderRepository.findOrderByCost(3000);
            jsonOrder.put("id", order.getId());
            jsonOrder.put("cost", order.getCost());
            jsonObject.put("delivery_type", order.getDelivery_type());
            Town arrtown = new Town();
            arrtown = townRepository.findTownByName("Вологда");
            JSONObject jsonArrTown = new JSONObject();
            jsonArrTown.put("id", arrtown.getId());
            jsonArrTown.put("name", arrtown.getName());
            jsonArrTown.put("info", arrtown.getInfo());
            Town departtown = new Town();
            departtown = townRepository.findTownByName("Москва");
            JSONObject jsonDepartTown = new JSONObject();
            jsonDepartTown.put("id", departtown.getId());
            jsonDepartTown.put("name", departtown.getName());
            jsonDepartTown.put("info", departtown.getInfo());
            jsonOrder.put("arrivaltown_id", order.getArrivaltown());
            jsonOrder.put("departtown_id", order.getDeparttown());
            jsonOrder.put("cost",20100);
            jsonObject.put("delivery_type", "экспресс");
            JSONObject jsonTransport = new JSONObject();
            Transport newTransport = new Transport();
            newTransport = transportRepository.findTransportByName("Мерседес");
            jsonTransport.put("id", newTransport.getId());
            jsonTransport.put("name",newTransport.getName());
            jsonTransport.put("capacity", newTransport.getCapacity());
            jsonTransport.put("wearout", newTransport.getWearout());
            jsonTransport.put("transport_type", newTransport.getTransport_type());
            Driver driver = new Driver();
            driver = driverRepository.findDriverBySurname("Костылев");
            JSONObject jsonDriver = new JSONObject();
            jsonDriver.put("id", driver.getId());
            jsonDriver.put("surname", driver.getSurname());
            jsonDriver.put("name", driver.getName());
            jsonTransport.put("driver_id", jsonDriver);
            jsonOrder.put("transport_id", order.getTransport());
            Client newClient = new Client();
            newClient = clientRepository.findClientByLogin("191764@edu.fa.ru");
            JSONObject jsonClient = new JSONObject();
            jsonClient.put("id", newClient.getId());
            jsonClient.put("surname",newClient.getSurname());
            jsonClient.put("name", newClient.getName());
            jsonClient.put("login", newClient.getLogin());
            jsonClient.put("phone", newClient.getPhone());
            jsonOrder.put("client_id", order.getClient_id());
            jsonObject.put("order_id", jsonOrder);
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8282/cargo/addCargo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonObject.toString())
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getCargoAll() {
    }

    @Test
    void deleteCargo() {
    }

    @Test
    void updateCargo() {
    }
}