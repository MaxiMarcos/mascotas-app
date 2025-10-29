package com.encontratumascota.perrosenlacalle.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionResponseDto {
    private Long id;
    private String ciudad;
    private String barrio;
    private String calle;
}
