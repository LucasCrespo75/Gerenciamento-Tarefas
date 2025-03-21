package com.user.user_service.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.user.user_service.api.dto.UsuarioDTO;
import com.user.user_service.domain.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioSalvo = usuarioService.salvar(usuarioDTO);
        return ResponseEntity.status(201).body(usuarioSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editar(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioEditado = usuarioService.editar(id, usuarioDTO);
        return ResponseEntity.ok(usuarioEditado);
    }


}
