package org.chatta.movieproyect.controller;
import org.chatta.movieproyect.exception.RecordNotFoundException;
import org.chatta.movieproyect.model.Palabra;
import org.chatta.movieproyect.services.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabras")

public class PalabraController {

    @Autowired
    private PalabraService palabraService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Palabra>> findAll() {
        List<Palabra> list = palabraService.getAllPalabras();

        /*esta fucion por defecto nos devolveria las palabras con sus definiciones relacionadas por el 1:M
        * pero como no nos piden las definiciones para esta parte las voy a quitar para que solo se muestren
        * cuando utilizemos el otro metodo, el de bucar una por id
        * asi lo pongo porque cuando tengas muchas palabras por los test aparece un tochaco de texto
        * cuando se haga la consulta va a segir apareciendo el campo pero este estara a null
        * otra forma de hacer esto hubirea sido crear una clase auxiliar DTO donde no
        * recogiera el campo pero eso seria mas codigo incesario*/

        return new ResponseEntity<List<Palabra>>(list, new HttpHeaders(),
                HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Palabra> getPalabraById(@PathVariable long id) throws RecordNotFoundException {

        //con la anotación @PathVariable le estoy diciendo que este argumento es el dato que va a poner cliente en la ruta (id)

        Palabra palabra = palabraService.getPalabraById(id);

        //recordemos que este metodo lanzaba una excepcion si el registro no existía

        return new ResponseEntity<Palabra>(palabra, new HttpHeaders(),
                HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> getPalabrasPorCategoria(@PathVariable String categoria) {
        List<Palabra> list = palabraService.getPalabrasPorCategoria(categoria);

        return new ResponseEntity<List<Palabra>>(list, new HttpHeaders(),
                HttpStatus.OK);
    }


    @CrossOrigin
    @GetMapping("/inicial/{letra}")
    public ResponseEntity<List<Palabra>> getPalabrasPorInicial(@PathVariable String letra) {
        List<Palabra> list = palabraService.getPalabrasByInicial(letra.toUpperCase());

        return new ResponseEntity<List<Palabra>>(list, new HttpHeaders(),
                HttpStatus.OK);
    }


    /*
    * este es el create palabra sin definiciones
    * */

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Palabra> createPalabra(@RequestBody Palabra palabra) {
        palabra.setDefinicions(null);
        Palabra nuevaPalabra = palabraService.addPalabra(palabra);
        return new ResponseEntity<Palabra>(nuevaPalabra, HttpStatus.CREATED);
    }


    @CrossOrigin
    @PostMapping("/condefiniciones")
    public ResponseEntity<Palabra> crearPalabraConDefiniciones(@RequestBody Palabra palabra) {
        Palabra nuevaPalabra = palabraService.addPalabraConDefiniciones(palabra);

        return new ResponseEntity<Palabra>(nuevaPalabra, HttpStatus.CREATED);
    }


    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Palabra> updatePalabra(@PathVariable Long id, @RequestBody Palabra palabra) throws RecordNotFoundException {
        Palabra updatedPalabra = palabraService.updatePalabra(id, palabra);
        return new ResponseEntity<Palabra>(updatedPalabra, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePalabra(@PathVariable Long id) throws RecordNotFoundException {
        palabraService.deletePalabra(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }



}
