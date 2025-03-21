package com.user.user_service.api.dto;

import java.time.LocalDateTime;

import com.user.user_service.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;

    // Construtor para converter entidade em DTO
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.dataCriacao = usuario.getDataCriacao();
    }

    public UsuarioDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = LocalDateTime.now(); // Ou null, dependendo da lógica
    }

}
