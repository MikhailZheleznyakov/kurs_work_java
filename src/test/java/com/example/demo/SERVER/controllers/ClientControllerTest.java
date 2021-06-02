package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.DemoApplication;
import com.example.demo.SERVER.repository.ClientRepository;
import com.example.demo.SERVER.tables.Client;
import org.aspectj.lang.annotation.Before;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    @Autowired
    public MockMvc mvc;
    @Autowired
    public ClientRepository clientRepository;

//    @AfterEach
//    void deleteTest(){
//        try{
//            clientRepository.delete(clientRepository.findClientByLogin("something@mail.ru"));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @Test
    void createClient() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("surname","ПЕТРОВ");
            jsonObject.put("name","ПЕТР");
            jsonObject.put("login","something@mail.ru");
            jsonObject.put("phone","+79115000700");
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8282/client/addClient")
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
    void getClientAll() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8282/client/getAllClient"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(jsonArray.length(), this.clientRepository.findAll().size());
                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateClient() {
        try {
            Client newClient = clientRepository.findClientByLogin("something@mail.ru");
            JSONObject jsonObject = new JSONObject();
            if (newClient==null){
                createClient();
            }
            newClient = clientRepository.findClientByLogin("something@mail.ru");
            jsonObject.put("id", newClient.getId());
            jsonObject.put("surname",newClient.getSurname());
            jsonObject.put("name", "МАКСИМ");
            jsonObject.put("login", newClient.getLogin());
            jsonObject.put("phone", newClient.getPhone());
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8282/client/updateClient/"+newClient.getId())
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
    void deleteClient() {
        try{
            Client newClient = clientRepository.findClientByLogin("something@mail.ru");
            System.out.println(newClient.getId());
            if (newClient == null){
                createClient();
            }
            this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8282/client/deleteClient/"+newClient.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}