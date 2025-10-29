package com.encontratumascota.perrosenlacalle.service.impl;

import com.encontratumascota.perrosenlacalle.dto.UbicacionRequestDto;
import com.encontratumascota.perrosenlacalle.dto.UbicacionResponseDto;
import com.encontratumascota.perrosenlacalle.entity.Ubicacion;
import com.encontratumascota.perrosenlacalle.mapper.UbicacionMapper;
import com.encontratumascota.perrosenlacalle.repository.UbicacionRepository;
import com.encontratumascota.perrosenlacalle.service.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UbicacionServiceImpl implements UbicacionService {

    private final UbicacionRepository ubicacionRepository;
    private final UbicacionMapper ubicacionMapper;

    @Override
    public UbicacionResponseDto crear(UbicacionRequestDto request) {
        Ubicacion entity = ubicacionMapper.toEntity(request);
        return ubicacionMapper.toResponseDto(ubicacionRepository.save(entity));
    }

    @Override
    public UbicacionResponseDto obtenerPorId(Long id) {
        return ubicacionRepository.findById(id)
                .map(ubicacionMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    public List<UbicacionResponseDto> listar() {
        return ubicacionRepository.findAll().stream()
                .map(ubicacionMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UbicacionResponseDto actualizar(Long id, UbicacionRequestDto request) {
        return ubicacionRepository.findById(id)
                .map(existing -> {
                    existing.setCiudad(request.getCiudad());
                    existing.setBarrio(request.getBarrio());
                    existing.setCalle(request.getCalle());
                    return ubicacionMapper.toResponseDto(ubicacionRepository.save(existing));
                })
                .orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        ubicacionRepository.deleteById(id);
    }
}
