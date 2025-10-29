package com.encontratumascota.perrosenlacalle.controller;

import com.encontratumascota.perrosenlacalle.dto.UbicacionRequestDto;
import com.encontratumascota.perrosenlacalle.dto.UbicacionResponseDto;
import com.encontratumascota.perrosenlacalle.service.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @PostMapping
    public ResponseEntity<UbicacionResponseDto> crear(@RequestBody UbicacionRequestDto request) {
        return new ResponseEntity<>(ubicacionService.crear(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionResponseDto> obtener(@PathVariable Long id) {
        UbicacionResponseDto resp = ubicacionService.obtenerPorId(id);
        return resp != null ? ResponseEntity.ok(resp) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UbicacionResponseDto>> listar() {
        return ResponseEntity.ok(ubicacionService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionResponseDto> actualizar(@PathVariable Long id, @RequestBody UbicacionRequestDto request) {
        UbicacionResponseDto resp = ubicacionService.actualizar(id, request);
        return resp != null ? ResponseEntity.ok(resp) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ubicacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
