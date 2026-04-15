package com.bootcamp_uninter.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {
    private Long idUsuario;
    private String nome;
    private String telefone;
    private String email;
    private EnderecoDTO endereco;
}