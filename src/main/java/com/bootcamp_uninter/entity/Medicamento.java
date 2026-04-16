package com.bootcamp_uninter.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private Long idMedicamento;

    @Column(nullable = false, length = 150)
    private String nomeComercial;

    @Column(nullable = false, length = 150)
    private String nomeGenerico;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, length = 100)
    private String formaUso;

    @Column(columnDefinition = "TEXT")
    private String observacao;
}