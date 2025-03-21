package com.user.user_service.domain.service;

import com.user.user_service.infraestructure.util.TarefaClient;
import org.springframework.stereotype.Service;

import com.user.user_service.model.Usuario;
import com.user.user_service.api.dto.UsuarioDTO;
import com.user.user_service.infraestructure.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TarefaClient tarefaClient;
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);


    public UsuarioDTO salvar(UsuarioDTO usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        Usuario novoUsuario = new Usuario(null, usuario.getNome(), usuario.getEmail(), null);
        return new UsuarioDTO(usuarioRepository.save(novoUsuario));
    }

    public void deletar(Long id) {
        log.info("Verificando se o usuário tem tarefas associadas (ID: {})", id);

        // Verifica se o usuário tem tarefas associadas
        boolean tarefaExistente = tarefaClient.existeTarefaPorUsuario(id);

        if (tarefaExistente) {
            // Caso o usuário tenha tarefas associadas, não deleta
            log.warn("Usuário com ID {} não pode ser deletado, pois tem tarefas associadas.", id);
            throw new IllegalArgumentException("Usuário não pode ser deletado, pois tem tarefas associadas.");
        }

        // Se não houver tarefas associadas, deleta o usuário
        usuarioRepository.deleteById(id);
        log.info("Usuário com ID {} foi deletado com sucesso.", id);
    }


    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return new UsuarioDTO(usuario);
    }

    public UsuarioDTO editar(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Atualize os campos necessários
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    public void limparTudo() {
        usuarioRepository.deleteAll();
    }


}
