package com.clustly.backend.servicio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServicioResponseDTO {
    private Long id;
    private String nombre;
    private Integer duracionMins;
    private Double precio;
    private boolean activo;
}