package com.task.task_service.api.controller;

import com.task.task_service.api.dto.TarefaDTO;
import com.task.task_service.domain.service.TarefaService; // Importe o serviço
import com.task.task_service.domain.status.StatusTarefa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService; // Injeção do serviço TarefaService

    @GetMapping("/existe/{usuarioId}")
    public ResponseEntity<Boolean> existeTarefaPorUsuario(@PathVariable Long usuarioId) {
        boolean existe = tarefaService.existeTarefaPorUsuario(usuarioId);
        return ResponseEntity.ok(existe);
    }


    //lista todas as atividades por colaborador que foi atribuido e por STATUS
    @GetMapping("/filtrar")
    public ResponseEntity<List<TarefaDTO>> filtrarTarefas(
            @RequestParam(required = false) StatusTarefa status,
            @RequestParam(required = false) Long usuarioId) {  // Parâmetro opcional tarefaId
        List<TarefaDTO> tarefasDTO = tarefaService.filtrarTarefas(status, usuarioId);  // Passa todos os parâmetros para o serviço
        return ResponseEntity.ok(tarefasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarTarefaPorId(@PathVariable Long id) {
        // Delegar a lógica para o serviço
        TarefaDTO tarefaDTO = tarefaService.buscarTarefaPorId(id);

        if (tarefaDTO != null) {
            return ResponseEntity.ok(tarefaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 se não encontrar
        }
    }


    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        List<TarefaDTO> tarefas = tarefaService.listAllTarefas(); // Chama o serviço para pegar todas as tarefas
        return ResponseEntity.ok(tarefas);
    }


    // Método para criar uma nova tarefa
    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        TarefaDTO novaTarefa = tarefaService.criar(tarefaDTO); // Chama o serviço para criar a tarefa
        return ResponseEntity.status(201).body(novaTarefa); // Retorna a nova tarefa criada com status 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> editar(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
        TarefaDTO tarefaEditada = tarefaService.editar(id, tarefaDTO);
        return ResponseEntity.ok(tarefaEditada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
