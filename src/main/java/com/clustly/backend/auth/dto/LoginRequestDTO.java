package com.clustly.backend.auth.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String contrasena;
}