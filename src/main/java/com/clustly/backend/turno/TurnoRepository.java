package com.clustly.backend.turno;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByNegocioIdAndFecha(Long negocioId, LocalDate fecha);
    List<Turno> findByNegocioId(Long negocioId);
}
