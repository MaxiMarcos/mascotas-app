package com.encontratumascota.perrosenlacalle.mapper;

import com.encontratumascota.perrosenlacalle.dto.MascotaRequestDto;
import com.encontratumascota.perrosenlacalle.dto.MascotaResponseDto;
import com.encontratumascota.perrosenlacalle.entity.Mascota;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapper {
    
    public Mascota toEntity(MascotaRequestDto dto) {
        return Mascota.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .fecha(dto.getFecha())
                .ubicacion(dto.getUbicacion())
                .imagen(dto.getImagen())
                .build();
    }
    
    public MascotaResponseDto toResponseDto(Mascota entity) {
        return MascotaResponseDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .fecha(entity.getFecha())
                .ubicacion(entity.getUbicacion())
                .imagen(entity.getImagen())
                .build();
    }
}