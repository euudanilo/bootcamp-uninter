package com.bootcamp_uninter.services;

import com.bootcamp_uninter.dtos.UsuarioMedicamentoCreateDTO;
import com.bootcamp_uninter.dtos.UsuarioMedicamentoResponseDTO;
import com.bootcamp_uninter.dtos.UsuarioMedicamentoUpdateDTO;
import com.bootcamp_uninter.entity.Medicamento;
import com.bootcamp_uninter.entity.Usuario;
import com.bootcamp_uninter.entity.UsuarioMedicamento;
import com.bootcamp_uninter.entity.UsuarioMedicamentoId;
import com.bootcamp_uninter.exceptions.BusinessException;
import com.bootcamp_uninter.exceptions.ResourceNotFoundException;
import com.bootcamp_uninter.repository.MedicamentoRepository;
import com.bootcamp_uninter.repository.UsuarioMedicamentoRepository;
import com.bootcamp_uninter.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioMedicamentoService {

    private final UsuarioMedicamentoRepository usuarioMedicamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final MedicamentoRepository medicamentoRepository;

    public UsuarioMedicamentoResponseDTO criar(UsuarioMedicamentoCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + dto.getIdUsuario()));

        Medicamento medicamento = medicamentoRepository.findById(dto.getIdMedicamento())
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado com id: " + dto.getIdMedicamento()));

        UsuarioMedicamentoId id = new UsuarioMedicamentoId(dto.getIdUsuario(), dto.getIdMedicamento());

        if (usuarioMedicamentoRepository.existsById(id)) {
            throw new BusinessException("Esse relacionamento usuário-medicamento já existe");
        }

        UsuarioMedicamento entidade = UsuarioMedicamento.builder()
                .id(id)
                .usuario(usuario)
                .medicamento(medicamento)
                .horarioUso(dto.getHorarioUso())
                .frequenciaUso(dto.getFrequenciaUso())
                .dosagem(dto.getDosagem())
                .dataHorarioAlerta(dto.getDataHorarioAlerta())
                .statusAlerta(dto.getStatusAlerta())
                .dataHorarioConsumo(dto.getDataHorarioConsumo())
                .confirmacaoConsumo(dto.getConfirmacaoConsumo())
                .build();

        return toResponseDTO(usuarioMedicamentoRepository.save(entidade));
    }

    public List<UsuarioMedicamentoResponseDTO> listarTodos() {
        return usuarioMedicamentoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public UsuarioMedicamentoResponseDTO buscarPorId(Long idUsuario, Long idMedicamento) {
        UsuarioMedicamento entidade = buscarEntidade(idUsuario, idMedicamento);
        return toResponseDTO(entidade);
    }

    public UsuarioMedicamentoResponseDTO atualizar(Long idUsuario, Long idMedicamento, UsuarioMedicamentoUpdateDTO dto) {
        UsuarioMedicamento entidade = buscarEntidade(idUsuario, idMedicamento);

        entidade.setHorarioUso(dto.getHorarioUso());
        entidade.setFrequenciaUso(dto.getFrequenciaUso());
        entidade.setDosagem(dto.getDosagem());
        entidade.setDataHorarioAlerta(dto.getDataHorarioAlerta());
        entidade.setStatusAlerta(dto.getStatusAlerta());
        entidade.setDataHorarioConsumo(dto.getDataHorarioConsumo());
        entidade.setConfirmacaoConsumo(dto.getConfirmacaoConsumo());

        return toResponseDTO(usuarioMedicamentoRepository.save(entidade));
    }

    public void deletar(Long idUsuario, Long idMedicamento) {
        UsuarioMedicamento entidade = buscarEntidade(idUsuario, idMedicamento);
        usuarioMedicamentoRepository.delete(entidade);
    }

    public List<UsuarioMedicamentoResponseDTO> listarPorUsuario(Long idUsuario) {
        return usuarioMedicamentoRepository.findByUsuarioIdUsuario(idUsuario)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private UsuarioMedicamento buscarEntidade(Long idUsuario, Long idMedicamento) {
        UsuarioMedicamentoId id = new UsuarioMedicamentoId(idUsuario, idMedicamento);

        return usuarioMedicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Relacionamento usuário-medicamento não encontrado: usuário=" + idUsuario + ", medicamento=" + idMedicamento
                ));
    }

    private UsuarioMedicamentoResponseDTO toResponseDTO(UsuarioMedicamento entidade) {
        return UsuarioMedicamentoResponseDTO.builder()
                .idUsuario(entidade.getUsuario().getIdUsuario())
                .idMedicamento(entidade.getMedicamento().getIdMedicamento())
                .nomeUsuario(entidade.getUsuario().getNome())
                .nomeMedicamento(entidade.getMedicamento().getNomeComercial())
                .horarioUso(entidade.getHorarioUso())
                .frequenciaUso(entidade.getFrequenciaUso())
                .dosagem(entidade.getDosagem())
                .dataHorarioAlerta(entidade.getDataHorarioAlerta())
                .statusAlerta(entidade.getStatusAlerta())
                .dataHorarioConsumo(entidade.getDataHorarioConsumo())
                .confirmacaoConsumo(entidade.getConfirmacaoConsumo())
                .build();
    }
}