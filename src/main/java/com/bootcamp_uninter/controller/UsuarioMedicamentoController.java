package com.bootcamp_uninter.controller;

import com.bootcamp_uninter.dtos.UsuarioMedicamentoCreateDTO;
import com.bootcamp_uninter.dtos.UsuarioMedicamentoResponseDTO;
import com.bootcamp_uninter.dtos.UsuarioMedicamentoUpdateDTO;
import com.bootcamp_uninter.services.UsuarioMedicamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-medicamentos")
@RequiredArgsConstructor
public class UsuarioMedicamentoController {

    private final UsuarioMedicamentoService usuarioMedicamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioMedicamentoResponseDTO criar(@Valid @RequestBody UsuarioMedicamentoCreateDTO dto) {
        return usuarioMedicamentoService.criar(dto);
    }

    @GetMapping
    public List<UsuarioMedicamentoResponseDTO> listarTodos() {
        return usuarioMedicamentoService.listarTodos();
    }

    @GetMapping("/{idUsuario}/{idMedicamento}")
    public UsuarioMedicamentoResponseDTO buscarPorId(
            @PathVariable Long idUsuario,
            @PathVariable Long idMedicamento
    ) {
        return usuarioMedicamentoService.buscarPorId(idUsuario, idMedicamento);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<UsuarioMedicamentoResponseDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return usuarioMedicamentoService.listarPorUsuario(idUsuario);
    }

    @PutMapping("/{idUsuario}/{idMedicamento}")
    public UsuarioMedicamentoResponseDTO atualizar(
            @PathVariable Long idUsuario,
            @PathVariable Long idMedicamento,
            @Valid @RequestBody UsuarioMedicamentoUpdateDTO dto
    ) {
        return usuarioMedicamentoService.atualizar(idUsuario, idMedicamento, dto);
    }

    @DeleteMapping("/{idUsuario}/{idMedicamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(
            @PathVariable Long idUsuario,
            @PathVariable Long idMedicamento
    ) {
        usuarioMedicamentoService.deletar(idUsuario, idMedicamento);
    }
}