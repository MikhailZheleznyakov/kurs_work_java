package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.TownRepository;
import com.example.demo.SERVER.tables.Driver;
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
class TownControllerTest {
    @Autowired
    public MockMvc mvc;
    @Autowired
    public TownRepository townRepository;

    @Test
    void addTown() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","Калининград");
            jsonObject.put("info","Кенинсберг");
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8282/town/addTown")
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
    void getTownAll() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8282/town/getAllTown"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(jsonArray.length(), this.townRepository.findAll().size());
                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteTown() {
        try{
            Town newTown = townRepository.findTownByName("Калининград");
            if (newTown == null){
                addTown();
            }
            newTown = townRepository.findTownByName("Калининград");
            this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8282/town/deleteTown/"+newTown.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateTown() {
        try{
            Town newTown = townRepository.findTownByName("Калининград");
            JSONObject jsonObject = new JSONObject();
            if (newTown == null){
                addTown();
            }
            newTown = townRepository.findTownByName("Калининград");
            jsonObject.put("id", newTown.getId());
            jsonObject.put("name", "Астрахань");
            jsonObject.put("info",newTown.getInfo());
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8282/town/updateTown/"+newTown.getId())
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
}