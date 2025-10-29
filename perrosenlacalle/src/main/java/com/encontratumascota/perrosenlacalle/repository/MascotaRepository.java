package com.encontratumascota.perrosenlacalle.repository;

import com.encontratumascota.perrosenlacalle.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    
    List<Mascota> findByUbicacion_BarrioIgnoreCase(String ubicacion);
    
    List<Mascota> findByDescripcionContainingIgnoreCase(String descripcion);
}
