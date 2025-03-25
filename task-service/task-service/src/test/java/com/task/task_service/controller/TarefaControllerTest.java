package com.task.task_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.task_service.api.dto.TarefaDTO;
import com.task.task_service.domain.service.TarefaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TarefaService tarefaService;

    private TarefaDTO tarefaDTO;


}
