package com.task.task_service.api.controller;

import com.task.task_service.api.dto.TarefaDTO;
import com.task.task_service.domain.service.TarefaService;
import com.task.task_service.domain.status.StatusTarefa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @Operation(summary = "Verificar se existe tarefa associada ao usuário", description = "Verifica se o usuário possui tarefas associadas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/existe/{usuarioId}")
    public ResponseEntity<Boolean> existeTarefaPorUsuario(@PathVariable Long usuarioId) {
        boolean existe = tarefaService.existeTarefaPorUsuario(usuarioId);
        if (!existe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok(existe);
    }

    @Operation(summary = "Filtrar tarefas", description = "Filtra tarefas com base no status e usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas listadas"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @GetMapping("/filtrar")
    public ResponseEntity<List<TarefaDTO>> filtrarTarefas(
            @RequestParam(required = false) StatusTarefa status,
            @RequestParam(required = false) Long usuarioId) {
        List<TarefaDTO> tarefasDTO = tarefaService.filtrarTarefas(status, usuarioId);
        if (tarefasDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tarefasDTO);
        }
        return ResponseEntity.ok(tarefasDTO);
    }

    @Operation(summary = "Buscar tarefa por ID", description = "Busca uma tarefa pelo ID fornecido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarTarefaPorId(@PathVariable Long id) {
        TarefaDTO tarefaDTO = tarefaService.buscarTarefaPorId(id);
        if (tarefaDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(tarefaDTO);
    }

    @Operation(summary = "Listar todas as tarefas", description = "Lista todas as tarefas cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas listadas"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        List<TarefaDTO> tarefas = tarefaService.listAllTarefas();
        return ResponseEntity.ok(tarefas);
    }

    @Operation(summary = "Criar tarefa", description = "Cria uma nova tarefa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        try {
            TarefaDTO novaTarefa = tarefaService.criar(tarefaDTO);
            return ResponseEntity.status(201).body(novaTarefa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Editar tarefa", description = "Edita uma tarefa existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa editada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "409", description = "Não pode editar a tarefa devido ao status")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> editar(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
        try {
            TarefaDTO tarefaEditada = tarefaService.editar(id, tarefaDTO);
            return ResponseEntity.ok(tarefaEditada);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Deletar tarefa", description = "Deleta uma tarefa pelo ID fornecido")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            tarefaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
