package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.RateRepository;
import com.example.demo.SERVER.repository.TownRepository;
import com.example.demo.SERVER.tables.Client;
import com.example.demo.SERVER.tables.Rate;
import com.example.demo.SERVER.tables.Town;
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
class RateControllerTest {
    @Autowired
    public MockMvc mvc;
    @Autowired
    public RateRepository rateRepository;
    @Autowired
    public TownRepository townRepository;

    @Test
    void addRate() {
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
            jsonObject.put("cost",20000);
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8282/rate/addRate")
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
    void getRateAll() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8282/rate/getAllRate"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(jsonArray.length(), this.rateRepository.findAll().size());
                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteRate() {
        try{
            Rate newRate = rateRepository.findRateByCost(Long.valueOf(20000));
            if (newRate == null){
                addRate();
                newRate = rateRepository.findRateByCost(Long.valueOf(20000));
            }
            this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8282/rate/deleteRate/"+newRate.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateRate() {
        try{
            Rate newRate = new Rate();
            newRate = rateRepository.findRateByCost(Long.valueOf(20000));
            if (newRate == null){
                addRate();
                newRate = rateRepository.findRateByCost(Long.valueOf(20000));
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", newRate.getId());
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
            jsonObject.put("cost", 1000);
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8282/rate/updateRate/"+newRate.getId())
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