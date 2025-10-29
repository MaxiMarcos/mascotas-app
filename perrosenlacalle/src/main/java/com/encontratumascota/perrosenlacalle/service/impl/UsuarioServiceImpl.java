package com.encontratumascota.perrosenlacalle.service.impl;

import com.encontratumascota.perrosenlacalle.dto.UsuarioRequestDto;
import com.encontratumascota.perrosenlacalle.dto.UsuarioResponseDto;
import com.encontratumascota.perrosenlacalle.entity.Usuario;
import com.encontratumascota.perrosenlacalle.mapper.UsuarioMapper;
import com.encontratumascota.perrosenlacalle.repository.UsuarioRepository;
import com.encontratumascota.perrosenlacalle.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponseDto crear(UsuarioRequestDto request) {
        Usuario usuario = usuarioMapper.toEntity(request);
        return usuarioMapper.toResponseDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponseDto obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    public List<UsuarioResponseDto> listar() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDto actualizar(Long id, UsuarioRequestDto request) {
        return usuarioRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(request.getNombre());
                    existing.setEmail(request.getEmail());
                    existing.setCelular(request.getCelular());
                    return usuarioMapper.toResponseDto(usuarioRepository.save(existing));
                })
                .orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
