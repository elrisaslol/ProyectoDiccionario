package org.chatta.movieproyect.repository;

import org.chatta.movieproyect.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PalabraRepository extends JpaRepository<Palabra,Long> {

    List<Palabra> findByCategoriaGramatical(String categoria);

    List<Palabra> findByTerminoStartingWith(String letra);
}
