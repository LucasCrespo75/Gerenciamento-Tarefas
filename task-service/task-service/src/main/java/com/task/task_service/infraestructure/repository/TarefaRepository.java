package com.task.task_service.infraestructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.task_service.model.Tarefa;
import com.task.task_service.domain.status.StatusTarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByStatusAndUsuarioId(StatusTarefa status, Long usuarioId);

    List<Tarefa> findByStatus(StatusTarefa status);

    List<Tarefa> findByUsuarioId(Long usuarioId);

    boolean existsByUsuarioId(Long usuarioId);
}

