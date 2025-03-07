package org.chatta.movieproyect.repository;

import org.chatta.movieproyect.model.Movie;
import org.chatta.movieproyect.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalabraRepository extends JpaRepository<Palabra,Long> {

}
