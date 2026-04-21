package com.clustly.backend.servicio.dto;

import lombok.Data;

@Data
public class ServicioRequestDTO {
    private String nombre;
    private Integer duracionMins;
    private Double precio;
}