package com.clustly.backend.servicio;

import com.clustly.backend.negocio.Negocio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servicios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer duracionMins;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private boolean activo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;
}