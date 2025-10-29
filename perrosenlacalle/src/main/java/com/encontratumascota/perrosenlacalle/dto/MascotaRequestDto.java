package com.encontratumascota.perrosenlacalle.dto;

import com.encontratumascota.perrosenlacalle.entity.Ubicacion;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MascotaRequestDto {

    private String nombre;
    private String descripcion;
    private LocalDateTime fecha;
    private Ubicacion ubicacion;
    private String imagen;
}
