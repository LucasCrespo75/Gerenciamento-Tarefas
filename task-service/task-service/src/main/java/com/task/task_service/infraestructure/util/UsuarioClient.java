package com.task.task_service.infraestructure.util;

import com.task.task_service.api.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-service", url = "http://localhost:8081")
public interface UsuarioClient {
    @GetMapping("/usuarios/{id}")
    UsuarioDTO buscarUsuario(@PathVariable Long id);
}
