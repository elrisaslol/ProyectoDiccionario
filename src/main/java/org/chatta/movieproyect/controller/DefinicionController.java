package org.chatta.movieproyect.controller;


import org.chatta.movieproyect.exception.RecordNotFoundException;
import org.chatta.movieproyect.model.Definicion;
import org.chatta.movieproyect.services.DefinicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/definicion")

public class DefinicionController {

    @Autowired
    private DefinicionService definicionService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Definicion>> findAll() {
        List<Definicion> list = definicionService.getAllDefiniciones();
        return new ResponseEntity<List<Definicion>>(list, new HttpHeaders(),
                HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Definicion> getDefinicionById(@PathVariable long id)  throws RecordNotFoundException{

        //con la anotación @PathVariable le estoy diciendo que este argumento es el dato que va a poner cliente en la ruta (id)

        Definicion definicion = definicionService.getDefinicionById(id);

        //recordemos que estemetodo lanzaba una excepcion si el registro no existía

        return new ResponseEntity<Definicion>(definicion, new HttpHeaders(),
                HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Definicion> createDefinicion(@RequestBody Definicion definicion) {
        Definicion nuevaDefinicion = definicionService.addDefinicion(definicion);
        return new ResponseEntity<Definicion>(nuevaDefinicion, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Definicion> updateDefinicion(@PathVariable Long id, @RequestBody Definicion definicion) throws RecordNotFoundException{
        Definicion updatedDefinicion = definicionService.updateDefinicion(id, definicion);
        return new ResponseEntity<Definicion>(updatedDefinicion, HttpStatus.OK);
    }



    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefinicion(@PathVariable Long id) throws RecordNotFoundException{
        definicionService.deleteDefinicion(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }


}
