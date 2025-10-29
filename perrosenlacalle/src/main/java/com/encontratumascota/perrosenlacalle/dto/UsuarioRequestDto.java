package com.encontratumascota.perrosenlacalle.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDto {
    private String nombre;
    private String email;
    private Integer celular;
}
