package com.bootcamp_uninter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "usuario_medicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioMedicamento {

    @EmbeddedId
    private UsuarioMedicamentoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idMedicamento")
    @JoinColumn(name = "id_medicamento", nullable = false)
    private Medicamento medicamento;

    @Column(nullable = false)
    private LocalTime horarioUso;

    @Column(nullable = false, length = 100)
    private String frequenciaUso;

    @Column(nullable = false, length = 100)
    private String dosagem;

    private LocalDateTime dataHorarioAlerta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusAlerta statusAlerta;

    private LocalDateTime dataHorarioConsumo;

    private Boolean confirmacaoConsumo;
}