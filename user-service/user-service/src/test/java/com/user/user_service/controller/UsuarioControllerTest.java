package com.user.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.user_service.api.controller.UsuarioController;
import com.user.user_service.api.dto.UsuarioDTO;
import com.user.user_service.domain.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setup() {
        doNothing().when(usuarioService).limparTudo();

        usuarioDTO = new UsuarioDTO(5L, "Emanoel moraes", "moraes@email.com", null);

        when(usuarioService.salvar(usuarioDTO)).thenReturn(usuarioDTO);
        when(usuarioService.buscarPorId(5L)).thenReturn(usuarioDTO);
        when(usuarioService.listar()).thenReturn(List.of(usuarioDTO));
    }

    /*
    @Test
    void deveSalvarUsuarioComSucesso() throws Exception {
        UsuarioDTO novoUsuario = new UsuarioDTO(null, "Novo Usuario3", "novo3@email.com", null);

        when(usuarioService.salvar(novoUsuario)).thenReturn(novoUsuario);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novoUsuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Novo Usuario3"))
                .andExpect(jsonPath("$.email").value("novo3@email.com"));
    }

     */

    @Test
    void deveBuscarUsuarioPorIdComSucesso() throws Exception {
        mockMvc.perform(get("/usuarios/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Emanoel Silva"));
    }

    @Test
    void deveListarUsuarios() throws Exception {
        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk());
    }

    @Test
    void deveEditarUsuarioComSucesso() throws Exception {
        UsuarioDTO usuarioEditado = new UsuarioDTO(5L, "Emanoel Silva", "emanoel.silva@email.com", null);

        when(usuarioService.salvar(usuarioEditado)).thenReturn(usuarioEditado);

        mockMvc.perform(put("/usuarios/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioEditado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Emanoel Silva"))
                .andExpect(jsonPath("$.email").value("emanoel.silva@email.com"));
    }

}
