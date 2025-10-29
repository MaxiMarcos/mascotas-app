package com.encontratumascota.perrosenlacalle.controller;

import com.encontratumascota.perrosenlacalle.dto.MascotaRequestDto;
import com.encontratumascota.perrosenlacalle.dto.MascotaResponseDto;
import com.encontratumascota.perrosenlacalle.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas-simple")
@CrossOrigin(origins = "*")
public class MascotaControllerSimple {
    
    @Autowired
    private MascotaService mascotaService;
    
    @PostMapping
    public ResponseEntity<MascotaResponseDto> crearMascota(@RequestBody MascotaRequestDto mascotaRequestDto) {
        MascotaResponseDto mascotaCreada = mascotaService.crearMascota(mascotaRequestDto);
        return new ResponseEntity<>(mascotaCreada, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<MascotaResponseDto>> buscarTodasLasMascotas() {
        List<MascotaResponseDto> mascotas = mascotaService.buscarTodasLasMascotas();
        return ResponseEntity.ok(mascotas);
    }
}



