package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.DriverRepository;
import com.example.demo.SERVER.repository.TransportRepository;
import com.example.demo.SERVER.tables.Driver;
import com.example.demo.SERVER.tables.Transport;
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
class TransportControllerTest {
    @Autowired
    public MockMvc mvc;
    @Autowired
    public TransportRepository transportRepository;
    @Autowired
    public DriverRepository driverRepository;

    @Test
    void addTransport() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","КОПЕЙКА");
            jsonObject.put("capacity", 500);
            jsonObject.put("wearout", 30);
            jsonObject.put("transport_type", "Грузовое авто");
            Driver driver = new Driver();
            driver = driverRepository.findDriverBySurname("Костылев");
            JSONObject jsonDriver = new JSONObject();
            jsonDriver.put("id", driver.getId());
            jsonDriver.put("surname", driver.getSurname());
            jsonDriver.put("name", driver.getName());
            jsonObject.put("driver_id", jsonDriver);

            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8282/transport/addTransport")
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
    void getTransportAll() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8282/transport/getAllTransport"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(jsonArray.length(), this.transportRepository.findAll().size());
                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteTransport() {
        try{
            Transport newTransport = transportRepository.findTransportByName("КОПЕЙКА");
            if (newTransport == null){
                addTransport();
            }
            newTransport = transportRepository.findTransportByName("КОПЕЙКА");
            this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8282/transport/deleteTransport/"+newTransport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateTransport() {
        try{
            Transport newTransport = transportRepository.findTransportByName("КОПЕЙКА");
            JSONObject jsonObject = new JSONObject();
            if (newTransport == null){
                addTransport();
            }
            newTransport = transportRepository.findTransportByName("КОПЕЙКА");
            jsonObject.put("id", newTransport.getId());
            jsonObject.put("name",newTransport.getName());
            jsonObject.put("capacity", 100);
            jsonObject.put("wearout", newTransport.getWearout());
            jsonObject.put("transport_type", newTransport.getTransport_type());
            Driver driver = new Driver();
            driver = driverRepository.findDriverBySurname("Костылев");
            JSONObject jsonDriver = new JSONObject();
            jsonDriver.put("id", driver.getId());
            jsonDriver.put("surname", driver.getSurname());
            jsonDriver.put("name", driver.getName());
            jsonObject.put("driver", jsonDriver);
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8282/transport/updateTransport/"+newTransport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonObject.toString())
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}