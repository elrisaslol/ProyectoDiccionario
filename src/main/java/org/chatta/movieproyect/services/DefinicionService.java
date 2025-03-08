package org.chatta.movieproyect.services;
import org.chatta.movieproyect.exception.RecordNotFoundException;
import org.chatta.movieproyect.model.Definicion;
import org.chatta.movieproyect.repository.DefinicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DefinicionService {


    @Autowired
    private DefinicionRepository definicionRepository;

    public List<Definicion> getAllDefiniciones(){

        List<Definicion> definiciones = definicionRepository.findAll();

        if(!definiciones.isEmpty()){
            return definiciones;
        }else{
            return new ArrayList<>();
        }

    }

    public Definicion getDefinicionById(long id) throws RecordNotFoundException{

        Optional<Definicion> definicion = definicionRepository.findById(id);

        if(definicion.isPresent()){
            return definicion.get();
        }else{
            throw new RecordNotFoundException("no existe la definicion con id " + id, id);
        }

    }

    public Definicion addDefinicion(Definicion definicion){
        return definicionRepository.save(definicion);
    }

    public Definicion updateDefinicion(long id, Definicion definicion) throws RecordNotFoundException {
        if (definicion.getId()!=null){
            Optional<Definicion> definicionOptional = definicionRepository.findById(id);
            if (definicionOptional.isPresent()){
                Definicion newdefinicion = definicionOptional.get();
                newdefinicion.setDescripcion(definicion.getDescripcion());
                newdefinicion.setEjemplo(definicion.getEjemplo());
                newdefinicion.setPalabra(definicion.getPalabra());

                newdefinicion=definicionRepository.save(newdefinicion);
                return newdefinicion;
            }else{
                throw new RecordNotFoundException("No existe definicion para el id: ",definicion.getId());
            }
        }else{
            throw new RecordNotFoundException("No hay id en la definicion a actualizar ",0l);
        }
    }


    public void deleteDefinicion(long id) throws RecordNotFoundException {
        Optional<Definicion> definicionOptional = definicionRepository.findById(id);
        if (definicionOptional.isPresent()){
            definicionRepository.delete(definicionOptional.get());
            //movieRepository.deleteById(id); //esta linea y la anterior hacen lo mismo, borran la peli, usando distintas formas
        }else{
            throw new RecordNotFoundException("No existe definicion para el id: ",id);
        }
    }


}
