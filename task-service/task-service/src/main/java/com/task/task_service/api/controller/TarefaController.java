package com.task.task_service.api.controller;

import com.task.task_service.api.dto.TarefaDTO;
import com.task.task_service.domain.service.TarefaService;
import com.task.task_service.domain.status.StatusTarefa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @Operation(summary = "Verifica se existem tarefas para um determinado usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna true se existirem tarefas, false caso contrário."),
            @ApiResponse(responseCode = "400", description = "ID do usuário inválido.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content)
    })
    @GetMapping("/existe/{usuarioId}")
    public ResponseEntity<Boolean> existeTarefaPorUsuario(@PathVariable Long usuarioId) {
        try {
            boolean existe = tarefaService.existeTarefaPorUsuario(usuarioId);
            return ResponseEntity.ok(existe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(false); // Ou outra resposta apropriada
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(false); // Ou outra resposta apropriada
        }
    }

    @Operation(summary = "Filtra tarefas por status e/ou ID do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarefas filtradas."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content)
    })
    @GetMapping("/filtrar")
    public ResponseEntity<List<TarefaDTO>> filtrarTarefas(
            @RequestParam(required = false) StatusTarefa status,
            @RequestParam(required = false) Long usuarioId) {
        try {
            List<TarefaDTO> tarefasDTO = tarefaService.filtrarTarefas(status, usuarioId);
            return ResponseEntity.ok(tarefasDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @Operation(summary = "Busca uma tarefa por seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada.",
                    content = @Content(schema = @Schema(implementation = TarefaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada.", content = @Content),
            @ApiResponse(responseCode = "400", description = "ID da tarefa inválido.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarTarefaPorId(@PathVariable Long id) {
        try {
            TarefaDTO tarefaDTO = tarefaService.buscarTarefaPorId(id);
            if (tarefaDTO != null) {
                return ResponseEntity.ok(tarefaDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @Operation(summary = "Lista todas as tarefas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de todas as tarefas.",
                    content = @Content(schema = @Schema(implementation = List.class, subTypes = {TarefaDTO.class}))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        try {
            List<TarefaDTO> tarefas = tarefaService.listAllTarefas();
            return ResponseEntity.ok(tarefas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @Operation(summary = "Cria uma nova tarefa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso.",
                    content = @Content(schema = @Schema(implementation = TarefaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados da tarefa inválidos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content)
    })
    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        try {
            TarefaDTO novaTarefa = tarefaService.criar(tarefaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @Operation(summary = "Edita uma tarefa existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa editada com sucesso.",
                    content = @Content(schema = @Schema(implementation = TarefaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados da tarefa inválidos ou tarefa não pode ser editada neste status.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> editar(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
        try {
            TarefaDTO tarefaEditada = tarefaService.editar(id, tarefaDTO);
            return ResponseEntity.ok(tarefaEditada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Ou outra resposta apropriada
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @Operation(summary = "Deleta uma tarefa por seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada.", content = @Content),
            @ApiResponse(responseCode = "400", description = "ID da tarefa inválido.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            tarefaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}