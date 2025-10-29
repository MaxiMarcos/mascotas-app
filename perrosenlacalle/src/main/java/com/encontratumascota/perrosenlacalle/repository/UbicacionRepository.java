package com.encontratumascota.perrosenlacalle.repository;

import com.encontratumascota.perrosenlacalle.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

    Optional<Ubicacion> findByBarrio(String barrio);
}
