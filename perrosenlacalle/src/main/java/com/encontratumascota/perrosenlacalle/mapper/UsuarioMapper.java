package com.encontratumascota.perrosenlacalle.mapper;

import com.encontratumascota.perrosenlacalle.dto.UsuarioRequestDto;
import com.encontratumascota.perrosenlacalle.dto.UsuarioResponseDto;
import com.encontratumascota.perrosenlacalle.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDto dto) {
        return Usuario.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .celular(dto.getCelular())
                .build();
    }

    public UsuarioResponseDto toResponseDto(Usuario entity) {
        return UsuarioResponseDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .celular(entity.getCelular())
                .build();
    }
}
