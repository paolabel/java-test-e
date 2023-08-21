package com.e.javatest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class RegistryOfficeStateMvcTests {

    @Autowired private MockMvc mvc;

    private static final int MAX_ID_LENGTH = 20;
    private static final int MAX_NAME_LENGTH = 50;
    private static final String ROOT_PATH = "/situacao-cartorio";

    @Test
    public void testIfCreateReturns400WhenIdIsTooLong() throws Exception {
        JSONObject idTooLongRequest = new JSONObject();
        idTooLongRequest.put("id", "a".repeat(MAX_ID_LENGTH + 1));
        idTooLongRequest.put("nome", "nome");
        mvc.perform(
                        post(ROOT_PATH)
                                .content(idTooLongRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenNameIsTooLong() throws Exception {
        JSONObject nameTooLongRequest = new JSONObject();
        nameTooLongRequest.put("id", "id");
        nameTooLongRequest.put("nome", "a".repeat(MAX_NAME_LENGTH + 1));
        mvc.perform(
                        post(ROOT_PATH)
                                .content(nameTooLongRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenIdIsEmpty() throws Exception {
        JSONObject emptyIdRequest = new JSONObject();
        emptyIdRequest.put("id", "");
        emptyIdRequest.put("nome", "nome2");
        mvc.perform(
                        post(ROOT_PATH)
                                .content(emptyIdRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenNameIsEmpty() throws Exception {
        JSONObject emptyNameRequest = new JSONObject();
        emptyNameRequest.put("id", "id2");
        emptyNameRequest.put("nome", "");
        mvc.perform(
                        post(ROOT_PATH)
                                .content(emptyNameRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenIdIsRepeated() throws Exception {
        String repeatId = "repeatId";
        JSONObject repeatIdRequest = new JSONObject();
        repeatIdRequest.put("id", repeatId);
        repeatIdRequest.put("nome", "name1");
        mvc.perform(
                        post(ROOT_PATH)
                                .content(repeatIdRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        JSONObject repeatIdRequest2 = new JSONObject();
        repeatIdRequest2.put("id", repeatId);
        repeatIdRequest2.put("nome", "name2");
        mvc.perform(
                post(ROOT_PATH)
                        .content(repeatIdRequest2.toString())
                        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenNameIsRepeated() throws Exception {
        String repeatName = "repeat name";
        JSONObject repeatNameRequest = new JSONObject();
        repeatNameRequest.put("id", "idRepeatName1");
        repeatNameRequest.put("nome", repeatName);
        mvc.perform(
                        post(ROOT_PATH)
                                .content(repeatNameRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        JSONObject repeatNameRequest2 = new JSONObject();
        repeatNameRequest2.put("id", "idRepeatName2");
        repeatNameRequest2.put("nome", repeatName);
        mvc.perform(
                post(ROOT_PATH)
                        .content(repeatNameRequest2.toString())
                        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns201WhenRequestIsCorrect() throws Exception {
        JSONObject correctRequest = new JSONObject();
        correctRequest.put("id", "correto");
        correctRequest.put("nome", "correto");
        mvc.perform(
                        post(ROOT_PATH)
                                .content(correctRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
