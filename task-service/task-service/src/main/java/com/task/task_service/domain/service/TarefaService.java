package com.task.task_service.domain.service;

import com.task.task_service.api.dto.UsuarioDTO;
import com.task.task_service.infraestructure.util.UsuarioClient;
import org.springframework.stereotype.Service;

import com.task.task_service.model.Tarefa;
import com.task.task_service.api.dto.TarefaDTO;
import com.task.task_service.infraestructure.repository.TarefaRepository;
import com.task.task_service.domain.status.StatusTarefa;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioClient usuarioClient;

    public TarefaDTO criar(TarefaDTO tarefaDTO) {
        if (tarefaDTO.getTitulo() == null || tarefaDTO.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa é obrigatório.");
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(tarefaDTO.getTitulo());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setStatus(tarefaDTO.getStatus());
        tarefa.setUsuarioId(tarefaDTO.getUsuarioId());
        tarefa.setDataLimite(tarefaDTO.getDataLimite());

        Tarefa tarefaSalva = tarefaRepository.save(tarefa);

        String usuarioNome = null;
        if (tarefaSalva.getUsuarioId() != null) {
            UsuarioDTO usuario = usuarioClient.buscarUsuario(tarefaSalva.getUsuarioId());
            usuarioNome = (usuario != null) ? usuario.getNome() : null;
        }

        return new TarefaDTO(tarefaSalva, usuarioNome);
    }


    public TarefaDTO editar(Long id, TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        if (!(tarefa.getStatus() == StatusTarefa.PENDENTE || tarefa.getStatus() == StatusTarefa.EM_ANDAMENTO)) {
            throw new IllegalStateException("Apenas tarefas com status 'Pendente' ou 'Em Andamento' podem ser editadas.");
        }

        tarefa.setTitulo(tarefaDTO.getTitulo());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setStatus(tarefaDTO.getStatus());
        tarefa.setDataLimite(tarefaDTO.getDataLimite());

        if (tarefaDTO.getUsuarioId() != null) {
            tarefa.setUsuarioId(tarefaDTO.getUsuarioId());
        }

        tarefa = tarefaRepository.save(tarefa);

        String usuarioNome = null;
        if (tarefa.getUsuarioId() != null) {
            UsuarioDTO usuario = usuarioClient.buscarUsuario(tarefa.getUsuarioId());
            usuarioNome = (usuario != null) ? usuario.getNome() : null;
        }

        return new TarefaDTO(tarefa, usuarioNome);
    }


    public List<TarefaDTO> listAllTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();

        return tarefas.stream().map(tarefa -> {
            String usuarioNome = null;
            if (tarefa.getUsuarioId() != null) {
                UsuarioDTO usuario = usuarioClient.buscarUsuario(tarefa.getUsuarioId());
                usuarioNome = (usuario != null) ? usuario.getNome() : null;
            }

            return new TarefaDTO(tarefa, usuarioNome);
        }).collect(Collectors.toList());
    }


    public List<TarefaDTO> filtrarTarefas(StatusTarefa status, Long usuarioId) {
        List<Tarefa> tarefas;

        if (status != null && usuarioId != null) {
            tarefas = tarefaRepository.findByStatusAndUsuarioId(status, usuarioId);
        } else if (status != null) {
            tarefas = tarefaRepository.findByStatus(status);
        } else if (usuarioId != null) {
            tarefas = tarefaRepository.findByUsuarioId(usuarioId);
        } else {
            tarefas = tarefaRepository.findAll();
        }

        return tarefas.stream().map(tarefa -> {
            TarefaDTO tarefaDTO = new TarefaDTO(tarefa);
            UsuarioDTO usuario = usuarioClient.buscarUsuario(tarefa.getUsuarioId());
            tarefaDTO.setUsuarioNome(usuario.getNome());
            return tarefaDTO;
        }).toList();
    }

    public boolean existeTarefaPorUsuario(Long usuarioId) {
        return tarefaRepository.existsByUsuarioId(usuarioId);
    }


    public void deletar(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
    }


    public TarefaDTO buscarTarefaPorId(Long id) {
        Optional<Tarefa> tarefaOpt = tarefaRepository.findById(id);
        if (tarefaOpt.isPresent()) {
            Tarefa tarefa = tarefaOpt.get();
            String usuarioNome = null;
            if (tarefa.getUsuarioId() != null) {
                UsuarioDTO usuario = usuarioClient.buscarUsuario(tarefa.getUsuarioId());
                usuarioNome = (usuario != null) ? usuario.getNome() : null;
            }
            return new TarefaDTO(tarefa, usuarioNome);
        } else {
            return null;
        }
    }
}
