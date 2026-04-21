package com.clustly.backend.servicio;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByNegocioId(Long negocioId);
    List<Servicio> findByNegocioIdAndActivoTrue(Long negocioId);
}