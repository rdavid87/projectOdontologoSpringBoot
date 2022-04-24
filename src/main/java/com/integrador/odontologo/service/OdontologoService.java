package com.integrador.odontologo.service;

import com.integrador.odontologo.entities.Odontologo;
import com.integrador.odontologo.exceptions.EntityNotFoundException;
import com.integrador.odontologo.repository.OdontologoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    @Autowired
    OdontologoRepository repository;

    public List<Odontologo> list(){
        return repository.findAll();
    }

    public Optional<Odontologo> searchById(Long id){
        Optional<Odontologo> odontologo = repository.findById(id);
        if (odontologo.isEmpty()) {
            var mensaje= "El odontologo cod id= "+ id.toString()+" no existe (searchById)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Odontologo.class, "id", id.toString());
        }
        return odontologo;
    }

    public void destroy(Long id) {
        Optional<Odontologo> odontologo=searchById(id);
        if (odontologo.isPresent()) {
            repository.deleteById(id);
        }else {
            var mensaje= "El odontologo cod id= "+ id.toString()+" no existe (destroy)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Odontologo.class, "id", id.toString());
        }
    }
    public Odontologo save(Odontologo odontologo){
        return repository.save(odontologo);
    }

    public Odontologo update(Odontologo odontologo) {
        if(searchById(odontologo.getId()).isPresent()) {
            return repository.save(odontologo);
        }else {
            var mensaje= "El odontologo cod id= "+  odontologo.getId().toString()+" no existe (update)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Odontologo.class, "id", odontologo.getId().toString());
        }
    }
}
