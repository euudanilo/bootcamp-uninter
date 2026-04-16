package com.bootcamp_uninter.repository;

import com.bootcamp_uninter.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}