package com.clustly.backend.servicio;

import com.clustly.backend.servicio.dto.ServicioRequestDTO;
import com.clustly.backend.servicio.dto.ServicioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/negocios/{negocioId}/servicios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class ServicioController {

    private final ServicioService servicioService;

    @PostMapping
    public ResponseEntity<ServicioResponseDTO> crear(@PathVariable Long negocioId,
                                                   @RequestBody ServicioRequestDTO request) {
        return ResponseEntity.ok(servicioService.crear(negocioId, request));
    }

    @GetMapping
    public ResponseEntity<List<ServicioResponseDTO>> listar(@PathVariable Long negocioId) {
        return ResponseEntity.ok(servicioService.listarPorNegocio(negocioId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> actualizar(@PathVariable Long negocioId,
                                                        @PathVariable Long id,
                                                        @RequestBody ServicioRequestDTO request) {
        return ResponseEntity.ok(servicioService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long negocioId,
                                          @PathVariable Long id) {
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}