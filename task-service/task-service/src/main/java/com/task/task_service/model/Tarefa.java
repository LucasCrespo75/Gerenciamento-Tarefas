package com.task.task_service.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.task.task_service.domain.status.StatusTarefa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarefas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTarefa status;

    @Column(name = "data_criacao",nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Column(name = "data_limite")
    private LocalDateTime dataLimite;

    @Column(name = "usuario_id",
            nullable = false)
    private Long usuarioId;
}
