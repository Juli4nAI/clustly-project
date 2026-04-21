package com.clustly.backend.negocio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "negocios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Negocio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String url;

    private String telefono;
    private String email;
    private String direccion;

    @Column(nullable = false)
    private boolean activo = true;
}