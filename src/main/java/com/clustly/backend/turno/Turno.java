package com.clustly.backend.turno;

import com.clustly.backend.negocio.Negocio;
import com.clustly.backend.servicio.Servicio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "turnos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = false)
    private String telefonoCliente;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTurno estado = EstadoTurno.PENDIENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;
}