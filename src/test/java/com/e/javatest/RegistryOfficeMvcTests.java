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
        ArrayList<String> assignmentList = new ArrayList<String>();
        assignmentList.add(assignmentId);
        idZeroRequest.put("idsAtribuicoes", assignmentList);

        mvc.perform(
                        post(ROOT_PATH)
                                .content(idZeroRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenNameIsTooLong() throws Exception {
        String stateId = "long name";
        String stateName = "long name";

        JSONObject stateCreationRequest = new JSONObject();
        stateCreationRequest.put("id", stateId);
        stateCreationRequest.put("nome", stateName);
        mvc.perform(
                        post("/situacao-cartorio")
                                .content(stateCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String assignmentId = "long name";
        String assignmentName = "long name";
        JSONObject assignmentCreationRequest = new JSONObject();
        assignmentCreationRequest.put("id", assignmentId);
        assignmentCreationRequest.put("nome", assignmentName);
        mvc.perform(
                        post("/atribuicao-cartorio")
                                .content(assignmentCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        JSONObject nameTooLongRequest = new JSONObject();
        nameTooLongRequest.put("id", 2);
        nameTooLongRequest.put("nome", "a".repeat(MAX_NAME_LENGTH + 1));
        nameTooLongRequest.put("idSituacao", stateId);
        ArrayList<String> assignmentList = new ArrayList<String>();
        assignmentList.add(assignmentId);
        nameTooLongRequest.put("idsAtribuicoes", assignmentList);

        mvc.perform(
                        post(ROOT_PATH)
                                .content(nameTooLongRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenNameIsEmpty() throws Exception {
        String stateId = "empty name";
        String stateName = "empty name";

        JSONObject stateCreationRequest = new JSONObject();
        stateCreationRequest.put("id", stateId);
        stateCreationRequest.put("nome", stateName);
        mvc.perform(
                        post("/situacao-cartorio")
                                .content(stateCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String assignmentId = "empty name";
        String assignmentName = "empty name";
        JSONObject assignmentCreationRequest = new JSONObject();
        assignmentCreationRequest.put("id", assignmentId);
        assignmentCreationRequest.put("nome", assignmentName);
        mvc.perform(
                        post("/atribuicao-cartorio")
                                .content(assignmentCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        JSONObject nameTooLongRequest = new JSONObject();
        nameTooLongRequest.put("id", 2);
        nameTooLongRequest.put("nome", "");
        nameTooLongRequest.put("idSituacao", stateId);
        ArrayList<String> assignmentList = new ArrayList<String>();
        assignmentList.add(assignmentId);
        nameTooLongRequest.put("idsAtribuicoes", assignmentList);

        mvc.perform(
                        post(ROOT_PATH)
                                .content(nameTooLongRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenObservationIsTooLong() throws Exception {
        String stateId = "long obs";
        String stateName = "long obs";

        JSONObject stateCreationRequest = new JSONObject();
        stateCreationRequest.put("id", stateId);
        stateCreationRequest.put("nome", stateName);
        mvc.perform(
                        post("/situacao-cartorio")
                                .content(stateCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String assignmentId = "long obs";
        String assignmentName = "long obs";
        JSONObject assignmentCreationRequest = new JSONObject();
        assignmentCreationRequest.put("id", assignmentId);
        assignmentCreationRequest.put("nome", assignmentName);
        mvc.perform(
                        post("/atribuicao-cartorio")
                                .content(assignmentCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        JSONObject obsTooLongRequest = new JSONObject();
        obsTooLongRequest.put("id", 3);
        obsTooLongRequest.put("nome", "long obs");
        obsTooLongRequest.put("observacao", "a".repeat(MAX_OBS_LENGTH + 1));
        obsTooLongRequest.put("idSituacao", stateId);
        ArrayList<String> assignmentList = new ArrayList<String>();
        assignmentList.add(assignmentId);
        obsTooLongRequest.put("idsAtribuicoes", assignmentList);

        mvc.perform(
                        post(ROOT_PATH)
                                .content(obsTooLongRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenAssignmentListIsEmpty() throws Exception {
        String stateId = "empty assignment";
        String stateName = "empty assignment";

        JSONObject stateCreationRequest = new JSONObject();
        stateCreationRequest.put("id", stateId);
        stateCreationRequest.put("nome", stateName);
        mvc.perform(
                        post("/situacao-cartorio")
                                .content(stateCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        JSONObject obsTooLongRequest = new JSONObject();
        obsTooLongRequest.put("id", 3);
        obsTooLongRequest.put("nome", "long obs");
        obsTooLongRequest.put("observacao", "a".repeat(MAX_OBS_LENGTH + 1));
        obsTooLongRequest.put("idSituacao", stateId);
        ArrayList<String> assignmentList = new ArrayList<String>();
        obsTooLongRequest.put("idsAtribuicoes", assignmentList);

        mvc.perform(
                        post(ROOT_PATH)
                                .content(obsTooLongRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenAssignmentDoesNotExist() throws Exception {
        String stateId = "wrong assignment";
        String stateName = "wrong assignment";

        JSONObject stateCreationRequest = new JSONObject();
        stateCreationRequest.put("id", stateId);
        stateCreationRequest.put("nome", stateName);
        mvc.perform(
                        post("/situacao-cartorio")
                                .content(stateCreationRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        JSONObject obsTooLongRequest = new JSONObject();
        obsTooLongRequest.put("id", 3);
        obsTooLongRequest.put("nome", "long obs");
        obsTooLongRequest.put("observacao", "a".repeat(MAX_OBS_LENGTH + 1));
        obsTooLongRequest.put("idSituacao", stateId);
        ArrayList<String> assignmentList = new ArrayList<String>();
        assignmentList.add("wrong assignment");
        obsTooLongRequest.put("idsAtribuicoes", assignmentList);

        mvc.perform(
                        post(ROOT_PATH)
                                .content(obsTooLongRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns400WhenStateDoesNotExist() throws Exception {
        String assignmentId = "wrong state";
        String assignmentName = "wrong state";
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
        correctRequest.put("observacao", "observação");
        correctRequest.put("idSituacao", "wrong state");
        ArrayList<String> assignmentList = new ArrayList<String>();
        assignmentList.add(assignmentId);
        correctRequest.put("idsAtribuicoes", new JSONArray(assignmentList));

        mvc.perform(
                        post(ROOT_PATH)
                                .content(correctRequest.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testIfCreateReturns201WhenRequestIsCorrect() throws Exception {
        String stateId = "situação correta";
        String stateName = "situação correta";
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
        correctRequest.put("observacao", "observação");
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
