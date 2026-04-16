package com.bootcamp_uninter.controller;


import com.bootcamp_uninter.dtos.MedicamentoCreateDTO;
import com.bootcamp_uninter.dtos.MedicamentoResponseDTO;
import com.bootcamp_uninter.dtos.MedicamentoUpdateDTO;
import com.bootcamp_uninter.services.MedicamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
@RequiredArgsConstructor
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicamentoResponseDTO criar(@Valid @RequestBody MedicamentoCreateDTO dto) {
        return medicamentoService.criar(dto);
    }

    @GetMapping
    public List<MedicamentoResponseDTO> listarTodos() {
        return medicamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public MedicamentoResponseDTO buscarPorId(@PathVariable Long id) {
        return medicamentoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public MedicamentoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody MedicamentoUpdateDTO dto) {
        return medicamentoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        medicamentoService.deletar(id);
    }
}