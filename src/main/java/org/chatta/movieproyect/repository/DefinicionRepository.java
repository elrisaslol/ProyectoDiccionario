package org.chatta.movieproyect.repository;

import org.chatta.movieproyect.model.Definicion;
import org.chatta.movieproyect.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefinicionRepository extends JpaRepository<Definicion,Long> {

}
