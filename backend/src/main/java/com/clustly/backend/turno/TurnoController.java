package com.clustly.backend.turno;

import com.clustly.backend.turno.dto.TurnoRequestDTO;
import com.clustly.backend.turno.dto.TurnoResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/negocios/{negocioId}/turnos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> crear(@PathVariable Long negocioId,
                                                @RequestBody TurnoRequestDTO request) {
        return ResponseEntity.ok(turnoService.crear(negocioId, request));
    }

    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> listar(@PathVariable Long negocioId) {
        return ResponseEntity.ok(turnoService.listarTodos(negocioId));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<TurnoResponseDTO>> listarPorFecha(
            @PathVariable Long negocioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(turnoService.listarPorFecha(negocioId, fecha));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<TurnoResponseDTO> cambiarEstado(@PathVariable Long negocioId,
                                                        @PathVariable Long id,
                                                        @RequestParam EstadoTurno estado) {
        return ResponseEntity.ok(turnoService.cambiarEstado(id, estado));
    }
}