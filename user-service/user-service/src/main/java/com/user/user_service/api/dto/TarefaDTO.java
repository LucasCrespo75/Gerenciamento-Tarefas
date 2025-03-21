package com.user.user_service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {
    private Long id;
    private String descricao;
    private String status;
}
