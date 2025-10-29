package com.encontratumascota.perrosenlacalle.repository;

import com.encontratumascota.perrosenlacalle.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
