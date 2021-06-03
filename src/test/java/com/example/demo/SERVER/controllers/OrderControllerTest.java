package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.*;
import com.example.demo.SERVER.tables.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
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
    void addOrder() {
        try {
            JSONObject jsonObject = new JSONObject();
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
            jsonObject.put("arrivaltown", jsonArrTown);
            jsonObject.put("departtown", jsonDepartTown);
            jsonObject.put("cost",20100);
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
            jsonTransport.put("driver", jsonDriver);
            jsonObject.put("transport", jsonTransport);
            Client newClient = new Client();
            newClient = clientRepository.findClientByLogin("191764@edu.fa.ru");
            JSONObject jsonClient = new JSONObject();
            jsonClient.put("id", newClient.getId());
            jsonClient.put("surname",newClient.getSurname());
            jsonClient.put("name", newClient.getName());
            jsonClient.put("login", newClient.getLogin());
            jsonClient.put("phone", newClient.getPhone());
            jsonObject.put("client_id", jsonClient);
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8282/order/addOrder")
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
    void getOrderAll() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8282/order/getAllOrder"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(jsonArray.length(), this.orderRepository.findAll().size());
                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteOrder() {
        try{
            Order order = new Order();
            order = orderRepository.findOrderByCost(20100.0);
            System.out.println(order.getClient_id());
            JSONObject jsonObject = new JSONObject();
            if (order == null){
                addOrder();
                order = orderRepository.findOrderByCost(20100.0);
            }
            this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8282/order/deleteOrder/"+order.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateOrder() {
        try{
            Order order = new Order();
            order = orderRepository.findOrderByCost(20100.0);
            System.out.println(order.getClient_id());
            JSONObject jsonObject = new JSONObject();
            if (order == null){
                addOrder();
                order = orderRepository.findOrderByCost(20100.0);
            }
            jsonObject.put("id", order.getId());
            jsonObject.put("delivery_type", "Стандарт++");
            jsonObject.put("cost", order.getCost());
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
            jsonObject.put("arrivaltown", jsonArrTown);
            jsonObject.put("departtown", jsonDepartTown);
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
            jsonObject.put("transport", jsonTransport);
            Client newClient = new Client();
            newClient = clientRepository.findClientByLogin("191764@edu.fa.ru");
            JSONObject jsonClient = new JSONObject();
            jsonClient.put("id", newClient.getId());
            jsonClient.put("surname",newClient.getSurname());
            jsonClient.put("name", newClient.getName());
            jsonClient.put("login", newClient.getLogin());
            jsonClient.put("phone", newClient.getPhone());
            jsonObject.put("client_id", jsonClient);
            System.out.println(jsonObject.toString());
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8282/order/updateOrder/"+order.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonObject.toString())
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}