package com.encontratumascota.perrosenlacalle.service.impl;

import com.encontratumascota.perrosenlacalle.dto.MascotaRequestDto;
import com.encontratumascota.perrosenlacalle.dto.MascotaResponseDto;
import com.encontratumascota.perrosenlacalle.entity.Mascota;
import com.encontratumascota.perrosenlacalle.entity.Ubicacion;
import com.encontratumascota.perrosenlacalle.mapper.MascotaMapper;
import com.encontratumascota.perrosenlacalle.repository.MascotaRepository;
import com.encontratumascota.perrosenlacalle.repository.UbicacionRepository;
import com.encontratumascota.perrosenlacalle.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaServiceImpl implements MascotaService {
    
    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;
    
    @Autowired
    private MascotaMapper mascotaMapper;
    
    @Override
    public MascotaResponseDto crearMascota(MascotaRequestDto mascotaRequestDto) {

        Ubicacion ubicacionExistente = ubicacionRepository.findByBarrio(mascotaRequestDto.getUbicacion().getBarrio()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontró una ubicación válida"));;

        // Si no se proporciona fecha, usar la fecha actual
        if (mascotaRequestDto.getFecha() == null) {
            mascotaRequestDto.setFecha(LocalDateTime.now());
        }

        mascotaRequestDto.setUbicacion(ubicacionExistente);
        
        Mascota mascota = mascotaMapper.toEntity(mascotaRequestDto);
        Mascota mascotaGuardada = mascotaRepository.save(mascota);
        return mascotaMapper.toResponseDto(mascotaGuardada);
    }
    
    @Override
    public List<MascotaResponseDto> buscarTodasLasMascotas() {
        List<Mascota> mascotas = mascotaRepository.findAll();
        return mascotas.stream()
                .map(mascotaMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<MascotaResponseDto> buscarMascotasPorUbicacion(String ubicacion) {
        List<Mascota> mascotas = mascotaRepository.findByUbicacion_BarrioIgnoreCase(ubicacion);
        return mascotas.stream()
                .map(mascotaMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<MascotaResponseDto> buscarMascotasPorDescripcion(String descripcion) {
        List<Mascota> mascotas = mascotaRepository.findByDescripcionContainingIgnoreCase(descripcion);
        return mascotas.stream()
                .map(mascotaMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}