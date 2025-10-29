package com.encontratumascota.perrosenlacalle.service;

import com.encontratumascota.perrosenlacalle.dto.MascotaRequestDto;
import com.encontratumascota.perrosenlacalle.dto.MascotaResponseDto;

import java.util.List;

public interface MascotaService {
    
    MascotaResponseDto crearMascota(MascotaRequestDto mascotaRequestDto);
    
    List<MascotaResponseDto> buscarTodasLasMascotas();
    
    List<MascotaResponseDto> buscarMascotasPorUbicacion(String ubicacion);
    
    List<MascotaResponseDto> buscarMascotasPorDescripcion(String descripcion);
}
