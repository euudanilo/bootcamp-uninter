package com.bootcamp_uninter.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicamentoResponseDTO {
    private Long idMedicamento;
    private String nomeComercial;
    private String nomeGenerico;
    private Integer quantidade;
    private String formaUso;
    private String observacao;
}