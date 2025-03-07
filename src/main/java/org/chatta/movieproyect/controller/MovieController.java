package org.chatta.movieproyect.controller;
import org.chatta.movieproyect.exception.RecordNotFoundException;
import org.chatta.movieproyect.model.Movie;
import org.chatta.movieproyect.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")


public class MovieController {

    @Autowired
    private MovieService movieService;


    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> list = movieService.getAllMovies();
        return new ResponseEntity<List<Movie>>(list, new HttpHeaders(),
                HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) throws RecordNotFoundException { //con la anotación @PathVariable le estoy diciendo que este argumento es el dato que va a poner cliente en la ruta (id)
                Movie movie = movieService.getMovieById(id); //recordemos que estemetodo lanzaba una excepcion si el registro no existía
        return new ResponseEntity<Movie>(movie, new HttpHeaders(),
                HttpStatus.OK);
    }


}
