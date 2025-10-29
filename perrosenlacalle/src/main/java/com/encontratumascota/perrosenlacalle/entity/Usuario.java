package com.encontratumascota.perrosenlacalle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    String email;
    Integer celular;

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    Mascota mascota;
}
