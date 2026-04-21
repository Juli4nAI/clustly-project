package com.clustly.backend.servicio;

import com.clustly.backend.negocio.Negocio;
import com.clustly.backend.negocio.NegocioRepository;
import com.clustly.backend.servicio.dto.ServicioRequestDTO;
import com.clustly.backend.servicio.dto.ServicioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioService {

    private final ServicioRepository servicioRepository;
    private final NegocioRepository negocioRepository;

    public ServicioResponseDTO crear(Long negocioId, ServicioRequestDTO request) {
        Negocio negocio = negocioRepository.findById(negocioId)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        Servicio servicio = Servicio.builder()
                .nombre(request.getNombre())
                .duracionMins(request.getDuracionMins())
                .precio(request.getPrecio())
                .activo(true)
                .negocio(negocio)
                .build();

        return toResponse(servicioRepository.save(servicio));
    }

    public List<ServicioResponseDTO> listarPorNegocio(Long negocioId) {
        return servicioRepository.findByNegocioIdAndActivoTrue(negocioId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ServicioResponseDTO actualizar(Long id, ServicioRequestDTO request) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        servicio.setNombre(request.getNombre());
        servicio.setDuracionMins(request.getDuracionMins());
        servicio.setPrecio(request.getPrecio());

        return toResponse(servicioRepository.save(servicio));
    }

    public void eliminar(Long id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        servicio.setActivo(false);
        servicioRepository.save(servicio);
    }

    private ServicioResponseDTO toResponse(Servicio s) {
        return ServicioResponseDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .duracionMins(s.getDuracionMins())
                .precio(s.getPrecio())
                .activo(s.isActivo())
                .build();
    }
}