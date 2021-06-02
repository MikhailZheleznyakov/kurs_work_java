package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.DriverRepository;
import com.example.demo.SERVER.tables.Driver;
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
class DriverControllerTest {
    @Autowired
    public MockMvc mvc;
    @Autowired
    public DriverRepository driverRepository;

    @Test
    void createDriver() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("surname","ПЕТРОВ");
            jsonObject.put("name","ПЕТР");
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8282/driver/addDriver")
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
    void getDriverAll() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8282/driver/getAllDriver"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(jsonArray.length(), this.driverRepository.findAll().size());
                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteDriver() {
        try{
            Driver newDriver = driverRepository.findDriverBySurname("ПЕТРОВ");
            if (newDriver == null){
                createDriver();
            }
            newDriver = driverRepository.findDriverBySurname("ПЕТРОВ");
            this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8282/driver/deleteDriver/"+newDriver.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateDriver() {
        try {
            Driver newDriver = driverRepository.findDriverBySurname("ПЕТРОВ");
            JSONObject jsonObject = new JSONObject();
            if (newDriver == null){
                createDriver();
            }
            newDriver = driverRepository.findDriverBySurname("ПЕТРОВ");
            jsonObject.put("id", newDriver.getId());
            jsonObject.put("surname",newDriver.getSurname());
            jsonObject.put("name", "МАКСИМ");
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8282/driver/updateDriver/"+newDriver.getId())
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