package com.bootcamp_uninter.services;

import com.bootcamp_uninter.dtos.EnderecoDTO;
import com.bootcamp_uninter.dtos.UsuarioCreateDTO;
import com.bootcamp_uninter.dtos.UsuarioResponseDTO;
import com.bootcamp_uninter.dtos.UsuarioUpdateDTO;
import com.bootcamp_uninter.entity.Endereco;
import com.bootcamp_uninter.entity.Usuario;
import com.bootcamp_uninter.exceptions.BusinessException;
import com.bootcamp_uninter.exceptions.ResourceNotFoundException;
import com.bootcamp_uninter.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO criar(UsuarioCreateDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Já existe um usuário com este email");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .endereco(toEndereco(dto.getEndereco()))
                .build();

        Usuario salvo = usuarioRepository.save(usuario);
        return toResponseDTO(salvo);
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));

        return toResponseDTO(usuario);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));

        usuarioRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    if (!u.getIdUsuario().equals(id)) {
                        throw new BusinessException("Já existe outro usuário com este email");
                    }
                });

        usuario.setNome(dto.getNome());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEmail(dto.getEmail());
        usuario.setEndereco(toEndereco(dto.getEndereco()));

        Usuario atualizado = usuarioRepository.save(usuario);
        return toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));

        usuarioRepository.delete(usuario);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .email(usuario.getEmail())
                .endereco(toEnderecoDTO(usuario.getEndereco()))
                .build();
    }

    private Endereco toEndereco(EnderecoDTO dto) {
        if (dto == null) return null;

        return Endereco.builder()
                .rua(dto.getRua())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .bairro(dto.getBairro())
                .cep(dto.getCep())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .build();
    }

    private EnderecoDTO toEnderecoDTO(Endereco endereco) {
        if (endereco == null) return null;

        EnderecoDTO dto = new EnderecoDTO();
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCep(endereco.getCep());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        return dto;
    }
}