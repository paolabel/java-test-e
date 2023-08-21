package com.e.javatest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.e.javatest.controller.RegistryOfficeController;
import java.util.ArrayList;
import org.json.JSONArray;
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
public class RegistryOfficeMvcTests {
    @Autowired private MockMvc mvc;
    @Autowired RegistryOfficeController controller;

    private static final int MIN_ID_VALUE = 1;
    private static final int MAX_NAME_LENGTH = 150;
    private static final int MAX_OBS_LENGTH = 250;
    private static final int MIN_ASSIGNMENT_LIST_SIZE = 1;
    private static final String ROOT_PATH = "/cartorio/";

    @Test
    public void testIfCreateReturns400WhenIdIsZero() throws Exception {
        String stateId = "zero";
        String stateName = "zero";

        JSONObject stateCreationRequest = new JSONObject();
        stateCreationRequest.put("id", stateId);
        stateCreationRequest.put("nome", stateName);
        mvc.perform(
                        post("/situacao-cartorio")
                                .content(stateCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String assignmentId = "zero";
        String assignmentName = "zero";
        JSONObject assignmentCreationRequest = new JSONObject();
        assignmentCreationRequest.put("id", assignmentId);
        assignmentCreationRequest.put("nome", assignmentName);
        mvc.perform(
                        post("/atribuicao-cartorio")
                                .content(assignmentCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        JSONObject idZeroRequest = new JSONObject();
        idZeroRequest.put("id", MIN_ID_VALUE - 1);
        idZeroRequest.put("nome", "nome");
        idZeroRequest.put("idSituacao", stateId);
        String[] assignmentList = {assignmentId};
        idZeroRequest.put("idsAtribuicoes", assignmentList);

        mvc.perform(
                        post(ROOT_PATH)
                                .content(idZeroRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns201WhenRequestIsCorrect() throws Exception {
        String stateId = "situação correta";
        String stateName = "situa\u00E7\u00E3o correta";
        JSONObject stateCreationRequest = new JSONObject();
        stateCreationRequest.put("id", stateId);
        stateCreationRequest.put("nome", stateName);
        mvc.perform(
                        post("/situacao-cartorio")
                                .content(stateCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String assignmentId = "atribuição correta";
        String assignmentName = "atribuição correta";
        JSONObject assignmentCreationRequest = new JSONObject();
        assignmentCreationRequest.put("id", assignmentId);
        assignmentCreationRequest.put("nome", assignmentName);
        mvc.perform(
                        post("/atribuicao-cartorio")
                                .content(assignmentCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        JSONObject correctRequest = new JSONObject();
        correctRequest.put("id", 1);
        correctRequest.put("nome", "nome");
        correctRequest.put("idSituacao", stateId);
        ArrayList<String> assignmentList = new ArrayList<String>();
        assignmentList.add(assignmentId);
        correctRequest.put("idsAtribuicoes", new JSONArray(assignmentList));

        mvc.perform(
                        post(ROOT_PATH)
                                .content(correctRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
