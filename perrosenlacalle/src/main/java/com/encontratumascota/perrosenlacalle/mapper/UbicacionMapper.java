package com.encontratumascota.perrosenlacalle.mapper;

import com.encontratumascota.perrosenlacalle.dto.UbicacionRequestDto;
import com.encontratumascota.perrosenlacalle.dto.UbicacionResponseDto;
import com.encontratumascota.perrosenlacalle.entity.Ubicacion;
import org.springframework.stereotype.Component;

@Component
public class UbicacionMapper {

    public Ubicacion toEntity(UbicacionRequestDto dto) {
        return Ubicacion.builder()
                .ciudad(dto.getCiudad())
                .barrio(dto.getBarrio())
                .calle(dto.getCalle())
                .build();
    }

    public UbicacionResponseDto toResponseDto(Ubicacion entity) {
        return UbicacionResponseDto.builder()
                .id(entity.getId())
                .ciudad(entity.getCiudad())
                .barrio(entity.getBarrio())
                .calle(entity.getCalle())
                .build();
    }
}
