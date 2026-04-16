package com.bootcamp_uninter.dtos;

import com.bootcamp_uninter.entity.StatusAlerta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class UsuarioMedicamentoCreateDTO {

    @NotNull(message = "idUsuario é obrigatório")
    private Long idUsuario;

    @NotNull(message = "idMedicamento é obrigatório")
    private Long idMedicamento;

    @NotNull(message = "Horário de uso é obrigatório")
    private LocalTime horarioUso;

    @NotBlank(message = "Frequência de uso é obrigatória")
    private String frequenciaUso;

    @NotBlank(message = "Dosagem é obrigatória")
    private String dosagem;

    private LocalDateTime dataHorarioAlerta;

    @NotNull(message = "Status do alerta é obrigatório")
    private StatusAlerta statusAlerta;

    private LocalDateTime dataHorarioConsumo;

    private Boolean confirmacaoConsumo;
}