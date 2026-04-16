package com.bootcamp_uninter.dtos;

import com.bootcamp_uninter.entity.StatusAlerta;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class UsuarioMedicamentoResponseDTO {
    private Long idUsuario;
    private Long idMedicamento;
    private String nomeUsuario;
    private String nomeMedicamento;
    private LocalTime horarioUso;
    private String frequenciaUso;
    private String dosagem;
    private LocalDateTime dataHorarioAlerta;
    private StatusAlerta statusAlerta;
    private LocalDateTime dataHorarioConsumo;
    private Boolean confirmacaoConsumo;
}