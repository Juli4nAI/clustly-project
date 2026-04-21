package com.clustly.backend.turno.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TurnoRequestDTO {
    private String nombreCliente;
    private String telefonoCliente;
    private LocalDate fecha;
    private LocalTime hora;
    private Long servicioId;
}