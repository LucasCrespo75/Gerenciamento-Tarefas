package com.user.user_service.infraestructure.util;

import com.user.user_service.api.dto.TarefaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "task-service", url = "http://localhost:8080")
public interface TarefaClient {
    // Verifica se existem tarefas associadas ao usuário
    @GetMapping("/tarefas/existe/{usuarioId}")
    boolean existeTarefaPorUsuario(@PathVariable Long usuarioId);

    // Obtém as tarefas associadas ao usuário com base no usuarioId
    @GetMapping("/tarefas/porUsuario/{usuarioId}")
    List<TarefaDTO> obterTarefasPorUsuario(@PathVariable Long usuarioId);
}
