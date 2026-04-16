package com.bootcamp_uninter.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicamentoCreateDTO {

    @NotBlank(message = "Nome comercial é obrigatório")
    private String nomeComercial;

    @NotBlank(message = "Nome genérico é obrigatório")
    private String nomeGenerico;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 0, message = "Quantidade não pode ser negativa")
    private Integer quantidade;

    @NotBlank(message = "Forma de uso é obrigatória")
    private String formaUso;

    private String observacao;
}