package org.chatta.movieproyect.services;
import org.chatta.movieproyect.exception.RecordNotFoundException;
import org.chatta.movieproyect.model.Definicion;
import org.chatta.movieproyect.model.Palabra;
import org.chatta.movieproyect.repository.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PalabraService {


    @Autowired
    private PalabraRepository palabraRepository;

    @Autowired
    private DefinicionService definicionService;

    public List<Palabra> getAllPalabras(){

        List<Palabra> palabras = palabraRepository.findAll();

        if(!palabras.isEmpty()){
            for (Palabra palabra : palabras) {
                palabra.setDefinicions(null);
            }
            return palabras;
        }else{
            return new ArrayList<>();
        }

    }

    public List<Palabra> getPalabrasPorCategoria(String categoria) {

        List<Palabra> palabras = palabraRepository.findByCategoriaGramatical(categoria);

        if(!palabras.isEmpty()){
            return palabras;
        }else{
            return new ArrayList<>();
        }

    }

    public List<Palabra> getPalabrasByInicial(String letra) {

        List<Palabra> palabras = palabraRepository.findByTerminoStartingWith(letra);

        if(!palabras.isEmpty()){
            return palabras;
        }else{
            return new ArrayList<>();
        }

    }

    public Palabra getPalabraById(long id) throws RecordNotFoundException{

        Optional<Palabra> palabra = palabraRepository.findById(id);

        if(palabra.isPresent()){
            return palabra.get();
        }else{
            throw new RecordNotFoundException("no existe la palabra con id " + id, id);
        }

    }

    public Palabra addPalabra(Palabra palabra){
        return palabraRepository.save(palabra);
    }


    public Palabra addPalabraConDefiniciones(Palabra palabra) {

        Palabra nuevaPalabra = palabraRepository.save(palabra);


        if (palabra.getDefinicions() != null) {
            for (Definicion definicion : palabra.getDefinicions()) {
                definicion.setPalabra(nuevaPalabra);
                definicionService.addDefinicion(definicion);
            }
        }

        return nuevaPalabra;
    }



    public Palabra updatePalabra(long id, Palabra palabra) throws RecordNotFoundException {
        if (palabra.getId()!=null){
            Optional<Palabra> palabraOptional = palabraRepository.findById(id);
            if (palabraOptional.isPresent()){
                Palabra newpalabra = palabraOptional.get();
                newpalabra.setTermino(palabra.getTermino());
                newpalabra.setCategoriaGramatical(palabra.getCategoriaGramatical());

                newpalabra=palabraRepository.save(newpalabra);
                return newpalabra;
            }else{
                throw new RecordNotFoundException("No existe palabra para el id: ",palabra.getId());
            }
        }else{
            throw new RecordNotFoundException("No hay id en la palabra a actualizar ",0l);
        }
    }

    public void deletePalabra(long id) throws RecordNotFoundException {
        Optional<Palabra> palabraOptional = palabraRepository.findById(id);
        if (palabraOptional.isPresent()){
            palabraRepository.delete(palabraOptional.get());
            //movieRepository.deleteById(id); //esta linea y la anterior hacen lo mismo, borran la peli, usando distintas formas
        }else{
            throw new RecordNotFoundException("No existe palabra para el id: ",id);
        }
    }

}
