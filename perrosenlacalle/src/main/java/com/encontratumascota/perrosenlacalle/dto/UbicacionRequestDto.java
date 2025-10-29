package com.encontratumascota.perrosenlacalle.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionRequestDto {
    private String ciudad;
    private String barrio;
    private String calle;
}
