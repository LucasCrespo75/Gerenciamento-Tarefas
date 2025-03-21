package com.task.task_service.service;

import com.task.task_service.domain.service.TarefaService;
import com.task.task_service.model.Tarefa;
import com.task.task_service.api.dto.TarefaDTO;
import com.task.task_service.infraestructure.repository.TarefaRepository;
import com.task.task_service.domain.status.StatusTarefa;
import com.task.task_service.infraestructure.util.UsuarioClient;
import com.task.task_service.api.dto.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @InjectMocks
    private TarefaService tarefaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarTarefaComSucesso() {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setTitulo("Nova Tarefa");
        tarefaDTO.setDescricao("Descrição da tarefa");
        tarefaDTO.setStatus(StatusTarefa.PENDENTE);
        tarefaDTO.setUsuarioId(1L);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNome("João");

        // Mock do serviço de usuário para retornar um usuário válido
        when(usuarioClient.buscarUsuario(1L)).thenReturn(usuarioDTO);
        // Mock do repositório para salvar a tarefa
        when(tarefaRepository.save(any(Tarefa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TarefaDTO resultado = tarefaService.criar(tarefaDTO);

        // Verifica se o resultado da tarefa não é nulo e se os dados são corretamente atribuídos
        assertNotNull(resultado);
        assertEquals("Nova Tarefa", resultado.getTitulo());
        assertEquals(StatusTarefa.PENDENTE, resultado.getStatus());
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }


    @Test
    void deveRetornarVerdadeiroSeTarefaExistePorUsuario() {
        // Mock do repositório para verificar a existência da tarefa
        when(tarefaRepository.existsByUsuarioId(1L)).thenReturn(true);

        // Verifica se o serviço retorna true
        boolean existe = tarefaService.existeTarefaPorUsuario(1L);

        assertTrue(existe);
        verify(tarefaRepository, times(1)).existsByUsuarioId(1L);
    }

    @Test
    void deveRetornarFalsoSeTarefaNaoExistePorUsuario() {
        // Mock do repositório para verificar a inexistência de tarefa
        when(tarefaRepository.existsByUsuarioId(2L)).thenReturn(false);

        // Verifica se o serviço retorna false
        boolean existe = tarefaService.existeTarefaPorUsuario(2L);

        assertFalse(existe);
        verify(tarefaRepository, times(1)).existsByUsuarioId(2L);
    }



    @Test
    void deveAtualizarTarefaComSucesso() {
        // Cria uma tarefa mockada
        Tarefa tarefaExistente = new Tarefa();
        tarefaExistente.setId(1L);
        tarefaExistente.setTitulo("Tarefa Antiga");
        tarefaExistente.setStatus(StatusTarefa.PENDENTE);

        // Cria um DTO com novos dados para atualizar
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setTitulo("Tarefa Atualizada");
        tarefaDTO.setStatus(StatusTarefa.CONCLUIDO);

        // Mock do repositório para buscar a tarefa
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefaExistente));
        // Mock do repositório para salvar a tarefa atualizada
        when(tarefaRepository.save(any(Tarefa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Chama o método do serviço para atualizar a tarefa
        TarefaDTO resultado = tarefaService.editar(1L, tarefaDTO);

        assertNotNull(resultado);
        assertEquals("Tarefa Atualizada", resultado.getTitulo());
        assertEquals(StatusTarefa.CONCLUIDO, resultado.getStatus());
    }

    @Test
    void deveLancarErroAoTentarAtualizarTarefaNaoExistente() {
        // Mock do repositório para retornar Optional.empty, indicando tarefa não encontrada
        when(tarefaRepository.findById(99L)).thenReturn(Optional.empty());

        // Espera que o serviço lance uma RuntimeException
        Exception exception = assertThrows(RuntimeException.class, () -> tarefaService.editar(99L, new TarefaDTO()));

        // Verifica a mensagem de erro esperada
        assertEquals("Tarefa não encontrada", exception.getMessage());
    }


    @Test
    void deveLancarErroAoTentarExcluirTarefaNaoExistente() {
        // Mock do repositório para retornar Optional.empty, indicando tarefa não encontrada
        when(tarefaRepository.findById(99L)).thenReturn(Optional.empty());

        // Espera que o serviço lance uma RuntimeException
        Exception exception = assertThrows(RuntimeException.class, () -> tarefaService.deletar(99L));

        // Verifica a mensagem de erro esperada
        assertEquals("Tarefa não encontrada", exception.getMessage());
    }
}
