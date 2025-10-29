package com.encontratumascota.perrosenlacalle.dto;

import com.encontratumascota.perrosenlacalle.entity.Ubicacion;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class MascotaResponseDto {
    
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fecha;
    private Ubicacion ubicacion;
    private String imagen;
}