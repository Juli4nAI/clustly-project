package com.clustly.backend.turno.dto;

import com.clustly.backend.turno.EstadoTurno;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class TurnoResponseDTO {
    private Long id;
    private String nombreCliente;
    private String telefonoCliente;
    private LocalDate fecha;
    private LocalTime hora;
    private EstadoTurno estado;
    private String nombreServicio;
    private Integer duracionMins;
}