package com.task.task_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.task_service.api.dto.TarefaDTO;
import com.task.task_service.domain.service.TarefaService;
import com.task.task_service.domain.status.StatusTarefa;
import com.task.task_service.infraestructure.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

//    @BeforeEach
//    void setUp() {
//        tarefaDTO = new TarefaDTO(1L, "Tarefa Teste", "Descrição Teste", StatusTarefa.PENDENTE, 10L);
//    }
//
//    @Test
//    void deveCriarTarefaComSucesso() throws Exception {
//        when(tarefaService.criar(any(TarefaDTO.class))).thenReturn(tarefaDTO);
//
//        ResultActions response = mockMvc.perform(post("/tarefas")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(tarefaDTO)));
//
//        response.andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(2))
//                .andExpect(jsonPath("$.titulo").value("titulo tal"));
//    }
//
//    @Test
//    void deveBuscarTarefaPorId() throws Exception {
//        mockMvc.perform(get("/tarefas/4"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.titulo").value("Nova Tarefa Atualizada2"));
//    }
//
//    @Test
//    void deveRetornarNotFoundSeTarefaNaoExistir() throws Exception {
//        when(tarefaService.buscarTarefaPorId(999L)).thenReturn(null);
//
//        mockMvc.perform(get("/tarefas/999"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void deveListarTarefas() throws Exception {
//        List<TarefaDTO> tarefas = Arrays.asList(tarefaDTO);
//        when(tarefaService.listAllTarefas()).thenReturn(tarefas);
//
//        mockMvc.perform(get("/tarefas"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].titulo").value(tarefaDTO.getTitulo()));
//    }
//
//    @Test
//    void deveFiltrarTarefasPorUsuarioEStatus() throws Exception {
//        List<TarefaDTO> tarefas = Arrays.asList(tarefaDTO);
//        when(tarefaService.filtrarTarefas(eq(StatusTarefa.PENDENTE), eq(10L))).thenReturn(tarefas);
//
//        mockMvc.perform(get("/tarefas/filtrar")
//                        .param("status", "PENDENTE")
//                        .param("usuarioId", "10"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].titulo").value(tarefaDTO.getTitulo()));
//    }
//
//    @Test
//    void deveEditarTarefa() throws Exception {
//        TarefaDTO tarefaEditada = new TarefaDTO(1L, "Tarefa Editada", "Descrição Editada", StatusTarefa.CONCLUIDO, 10L);
//        when(tarefaService.editar(eq(1L), any(TarefaDTO.class))).thenReturn(tarefaEditada);
//
//        mockMvc.perform(put("/tarefas/21")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(tarefaEditada)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.titulo").value("Tarefa Editada"));
//    }

//    @Test
//    void deveDeletarTarefa() throws Exception {
//        mockMvc.perform(delete("/tarefas/3"))
//                .andExpect(status().isNoContent());
//    }
}
