package org.chatta.movieproyect.services;

import org.chatta.movieproyect.exception.RecordNotFoundException;
import org.chatta.movieproyect.model.Movie;
import org.chatta.movieproyect.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies(){

        List<Movie> movies = movieRepository.findAll();

        if(!movies.isEmpty()){
            return movies;
        }else{
            return new ArrayList<>();
        }

    }

    public Movie getMovieById(int id){

        Optional<Movie> movie = movieRepository.findById(id);

        if(movie.isPresent()){
            return movie.get();
        }else{
            throw new RecordNotFoundException("no existe la pelicula con id " + id, id);
        }

    }

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie updateMovie(int id, Movie movie) throws RecordNotFoundException {
        if (movie.getId()!=null){
            Optional<Movie> movieOptional = movieRepository.findById(id);
            if (movieOptional.isPresent()){
                Movie newMovie = movieOptional.get();
                newMovie.setTitulo(movie.getTitulo());
                newMovie.setAño(movie.getAño());
                newMovie.setVoto(movie.getVoto());
                newMovie.setValoración(movie.getValoración());
                newMovie=movieRepository.save(newMovie);
                return newMovie;
            }else{
                throw new RecordNotFoundException("No existe Pelicula para el id: ",movie.getId());
            }
        }else{
            throw new RecordNotFoundException("No hay id en la peli a actualizar ",0l);
        }
    }

    public void deleteMovie(int id) throws RecordNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()){
            movieRepository.delete(movieOptional.get());
            //movieRepository.deleteById(id); //esta linea y la anterior hacen lo mismo, borran la peli, usando distintas formas
        }else{
            throw new RecordNotFoundException("No existe Pelicula para el id: ",id);
        }
    }





}
