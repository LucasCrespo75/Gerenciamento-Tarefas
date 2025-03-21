package com.task.task_service.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.task.task_service.model.Tarefa;
import com.task.task_service.domain.status.StatusTarefa;
import com.task.task_service.infraestructure.config.CustomLocalDateTimeDeserializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    private LocalDateTime dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)  // Aqui está a deserialização customizada
    private LocalDateTime dataLimite;

    private Long usuarioId; // Referência ao usuário responsável
    private String usuarioNome; // Nome do usuário associado

    // Construtor para converter entidade em DTO
    public TarefaDTO(Tarefa tarefa, String usuarioNome) {
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.status = tarefa.getStatus();
        this.dataCriacao = tarefa.getDataCriacao();
        this.dataLimite = tarefa.getDataLimite();
        this.usuarioId = tarefa.getUsuarioId();
        this.usuarioNome = usuarioNome;
    }

    public TarefaDTO(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.status = tarefa.getStatus();
        this.dataCriacao = tarefa.getDataCriacao();
        this.dataLimite = tarefa.getDataLimite();
        this.usuarioId = tarefa.getUsuarioId();
    }
    public TarefaDTO(Long id, String titulo, String descricao, StatusTarefa status, Long usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.usuarioId = usuarioId;
    }

}