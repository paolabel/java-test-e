package com.e.javatest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.e.javatest.controller.RegistryOfficeAssignmentController;
import com.e.javatest.request.AssignmentCreationRequest;
import com.e.javatest.response.AssignmentCreationResponse;
import java.util.Optional;
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
public class RegistryOfficeAssignmentMvcTests {
    @Autowired private MockMvc mvc;
    @Autowired RegistryOfficeAssignmentController controller;

    private static final int MAX_ID_LENGTH = 20;
    private static final int MAX_NAME_LENGTH = 50;
    private static final boolean DEFAULT_STATE_VALUE = true;
    private static final String ROOT_PATH = "/atribuicao-cartorio/";

    @Test
    public void testIfCreateReturns400WhenIdIsTooLong() throws Exception {
        JSONObject idTooLongRequest = new JSONObject();
        idTooLongRequest.put("id", "a".repeat(MAX_ID_LENGTH + 1));
        idTooLongRequest.put("nome", "nome");
        idTooLongRequest.put("situacao", false);
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
        nameTooLongRequest.put("situacao", false);
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
        emptyIdRequest.put("situacao", false);
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
        emptyNameRequest.put("situacao", false);
        mvc.perform(
                        post(ROOT_PATH)
                                .content(emptyNameRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testStateDefaultValueWhenStateIsNotSent() throws Exception {
        AssignmentCreationRequest request =
                new AssignmentCreationRequest("sem situação", "sem situação", Optional.empty());
        AssignmentCreationResponse response = controller.createRegistryOfficeState(request);
        assertEquals(DEFAULT_STATE_VALUE, response.getData().getState());
    }

    @Test
    public void testIfCreateReturns201WhenRequestIsCorrect() throws Exception {
        JSONObject correctRequest = new JSONObject();
        correctRequest.put("id", "correto");
        correctRequest.put("nome", "correto");
        correctRequest.put("situacao", false);
        mvc.perform(
                        post(ROOT_PATH)
                                .content(correctRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testIfUpdateReturns400WhenNameIsRepeated() throws Exception {
        String updateId = "toUpdateAssignment";
        String repeatName = "update repeat name assignment";
        JSONObject repeatNameRequest = new JSONObject();
        repeatNameRequest.put("id", "idUpdateRepeatName1");
        repeatNameRequest.put("nome", repeatName);
        mvc.perform(
                        post(ROOT_PATH)
                                .content(repeatNameRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        JSONObject repeatNameRequest2 = new JSONObject();
        repeatNameRequest2.put("id", updateId);
        repeatNameRequest2.put("nome", "nome original");
        mvc.perform(
                        post(ROOT_PATH)
                                .content(repeatNameRequest2.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        JSONObject updateNameRequest = new JSONObject();
        updateNameRequest.put("nome", repeatName);
        mvc.perform(
                        put(ROOT_PATH + updateId)
                                .content(updateNameRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
