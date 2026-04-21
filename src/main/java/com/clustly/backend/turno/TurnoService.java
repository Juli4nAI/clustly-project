package com.clustly.backend.turno;

import com.clustly.backend.negocio.Negocio;
import com.clustly.backend.negocio.NegocioRepository;
import com.clustly.backend.servicio.Servicio;
import com.clustly.backend.servicio.ServicioRepository;
import com.clustly.backend.turno.dto.TurnoRequestDTO;
import com.clustly.backend.turno.dto.TurnoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final NegocioRepository negocioRepository;
    private final ServicioRepository servicioRepository;

    public TurnoResponseDTO crear(Long negocioId, TurnoRequestDTO request) {
        Negocio negocio = negocioRepository.findById(negocioId)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));
        Servicio servicio = servicioRepository.findById(request.getServicioId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Turno turno = Turno.builder()
                .nombreCliente(request.getNombreCliente())
                .telefonoCliente(request.getTelefonoCliente())
                .fecha(request.getFecha())
                .hora(request.getHora())
                .estado(EstadoTurno.PENDIENTE)
                .servicio(servicio)
                .negocio(negocio)
                .build();

        return toResponse(turnoRepository.save(turno));
    }

    public List<TurnoResponseDTO> listarPorFecha(Long negocioId, LocalDate fecha) {
        return turnoRepository.findByNegocioIdAndFecha(negocioId, fecha)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TurnoResponseDTO> listarTodos(Long negocioId) {
        return turnoRepository.findByNegocioId(negocioId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TurnoResponseDTO cambiarEstado(Long id, EstadoTurno estado) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        turno.setEstado(estado);
        return toResponse(turnoRepository.save(turno));
    }

    private TurnoResponseDTO toResponse(Turno t) {
        return TurnoResponseDTO.builder()
                .id(t.getId())
                .nombreCliente(t.getNombreCliente())
                .telefonoCliente(t.getTelefonoCliente())
                .fecha(t.getFecha())
                .hora(t.getHora())
                .estado(t.getEstado())
                .nombreServicio(t.getServicio().getNombre())
                .duracionMins(t.getServicio().getDuracionMins())
                .build();
    }
}
