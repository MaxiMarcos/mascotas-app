package com.encontratumascota.perrosenlacalle.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    String descripcion;
    LocalDateTime fecha;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    String imagen;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    Ubicacion ubicacion;

}
