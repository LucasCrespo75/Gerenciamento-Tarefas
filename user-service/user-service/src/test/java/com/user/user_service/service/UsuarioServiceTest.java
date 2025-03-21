package com.user.user_service.service;

import com.user.user_service.domain.service.UsuarioService;
import com.user.user_service.model.Usuario;
import com.user.user_service.api.dto.UsuarioDTO;
import com.user.user_service.infraestructure.repository.UsuarioRepository;
import com.user.user_service.infraestructure.util.TarefaClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TarefaClient tarefaClient;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setup() {
        usuario = new Usuario(1L, "João Silva", "joao@email.com", null);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
        when(usuarioRepository.save(any())).thenReturn(usuario);

        UsuarioDTO resultado = usuarioService.salvar(new UsuarioDTO(usuario));

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
    }

    @Test
    void deveLancarExcecaoAoSalvarEmailDuplicado() {
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> usuarioService.salvar(new UsuarioDTO(usuario)));

        assertEquals("Email já cadastrado.", exception.getMessage());
    }

    @Test
    void deveDeletarUsuarioSemTarefas() {
        when(tarefaClient.existeTarefaPorUsuario(1L)).thenReturn(false);
        doNothing().when(usuarioRepository).deleteById(1L);

        assertDoesNotThrow(() -> usuarioService.deletar(1L));
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarUsuarioComTarefas() {
        when(tarefaClient.existeTarefaPorUsuario(1L)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> usuarioService.deletar(1L));

        assertEquals("Usuário não pode ser deletado, pois tem tarefas associadas.", exception.getMessage());
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioDTO resultado = usuarioService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoAoBuscarUsuarioNaoExistente() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> usuarioService.buscarPorId(99L));

        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    void deveEditarUsuarioComSucesso() {
        UsuarioDTO novoUsuarioDTO = new UsuarioDTO(1L, "Maria Silva", "maria@email.com");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any())).thenReturn(new Usuario(1L, "Maria Silva", "maria@email.com", null));

        UsuarioDTO resultado = usuarioService.editar(1L, novoUsuarioDTO);

        assertNotNull(resultado);
        assertEquals("Maria Silva", resultado.getNome());
        assertEquals("maria@email.com", resultado.getEmail());
    }

    @Test
    void deveListarUsuariosComSucesso() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<UsuarioDTO> usuarios = usuarioService.listar();

        assertFalse(usuarios.isEmpty());
        assertEquals(1, usuarios.size());
        assertEquals("João Silva", usuarios.get(0).getNome());
    }
}

