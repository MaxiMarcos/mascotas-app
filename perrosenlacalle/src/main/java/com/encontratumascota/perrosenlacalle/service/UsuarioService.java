package com.encontratumascota.perrosenlacalle.service;

import com.encontratumascota.perrosenlacalle.dto.UsuarioRequestDto;
import com.encontratumascota.perrosenlacalle.dto.UsuarioResponseDto;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDto crear(UsuarioRequestDto request);
    UsuarioResponseDto obtenerPorId(Long id);
    List<UsuarioResponseDto> listar();
    UsuarioResponseDto actualizar(Long id, UsuarioRequestDto request);
    void eliminar(Long id);
}
