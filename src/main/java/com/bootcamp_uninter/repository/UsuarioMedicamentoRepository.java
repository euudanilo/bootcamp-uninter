package com.bootcamp_uninter.repository;

import com.bootcamp_uninter.entity.UsuarioMedicamento;
import com.bootcamp_uninter.entity.UsuarioMedicamentoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioMedicamentoRepository extends JpaRepository<UsuarioMedicamento, UsuarioMedicamentoId> {
    List<UsuarioMedicamento> findByUsuarioIdUsuario(Long idUsuario);
    List<UsuarioMedicamento> findByMedicamentoIdMedicamento(Long idMedicamento);
}
