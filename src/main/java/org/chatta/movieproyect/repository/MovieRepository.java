package org.chatta.movieproyect.repository;

import org.chatta.movieproyect.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

}
