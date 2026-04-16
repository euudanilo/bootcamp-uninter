package com.bootcamp_uninter.services;

import com.bootcamp_uninter.dtos.MedicamentoCreateDTO;
import com.bootcamp_uninter.dtos.MedicamentoResponseDTO;
import com.bootcamp_uninter.dtos.MedicamentoUpdateDTO;
import com.bootcamp_uninter.entity.Medicamento;
import com.bootcamp_uninter.exceptions.ResourceNotFoundException;
import com.bootcamp_uninter.repository.MedicamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoResponseDTO criar(MedicamentoCreateDTO dto) {
        Medicamento medicamento = Medicamento.builder()
                .nomeComercial(dto.getNomeComercial())
                .nomeGenerico(dto.getNomeGenerico())
                .quantidade(dto.getQuantidade())
                .formaUso(dto.getFormaUso())
                .observacao(dto.getObservacao())
                .build();

        return toResponseDTO(medicamentoRepository.save(medicamento));
    }

    public List<MedicamentoResponseDTO> listarTodos() {
        return medicamentoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public MedicamentoResponseDTO buscarPorId(Long id) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado com id: " + id));

        return toResponseDTO(medicamento);
    }

    public MedicamentoResponseDTO atualizar(Long id, MedicamentoUpdateDTO dto) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado com id: " + id));

        medicamento.setNomeComercial(dto.getNomeComercial());
        medicamento.setNomeGenerico(dto.getNomeGenerico());
        medicamento.setQuantidade(dto.getQuantidade());
        medicamento.setFormaUso(dto.getFormaUso());
        medicamento.setObservacao(dto.getObservacao());

        return toResponseDTO(medicamentoRepository.save(medicamento));
    }

    public void deletar(Long id) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado com id: " + id));

        medicamentoRepository.delete(medicamento);
    }

    private MedicamentoResponseDTO toResponseDTO(Medicamento medicamento) {
        return MedicamentoResponseDTO.builder()
                .idMedicamento(medicamento.getIdMedicamento())
                .nomeComercial(medicamento.getNomeComercial())
                .nomeGenerico(medicamento.getNomeGenerico())
                .quantidade(medicamento.getQuantidade())
                .formaUso(medicamento.getFormaUso())
                .observacao(medicamento.getObservacao())
                .build();
    }
}