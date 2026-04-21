package com.clustly.backend.auth.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String nombre;
    private String email;
    private String contrasena;
    private String nombreNegocio;
    private String urlNegocio;
    private String telefonoNegocio;
}