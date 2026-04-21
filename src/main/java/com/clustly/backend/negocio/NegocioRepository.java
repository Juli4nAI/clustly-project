package com.clustly.backend.negocio;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NegocioRepository extends JpaRepository<Negocio, Long> {
    Optional<Negocio> findByUrl(String url);
    boolean existsByUrl(String url);
}
