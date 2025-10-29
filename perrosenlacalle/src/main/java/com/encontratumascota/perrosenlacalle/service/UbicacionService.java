package com.encontratumascota.perrosenlacalle.service;

import com.encontratumascota.perrosenlacalle.dto.UbicacionRequestDto;
import com.encontratumascota.perrosenlacalle.dto.UbicacionResponseDto;

import java.util.List;

public interface UbicacionService {
    UbicacionResponseDto crear(UbicacionRequestDto request);
    UbicacionResponseDto obtenerPorId(Long id);
    List<UbicacionResponseDto> listar();
    UbicacionResponseDto actualizar(Long id, UbicacionRequestDto request);
    void eliminar(Long id);
}
