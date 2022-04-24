package com.integrador.odontologo.service;


import com.integrador.odontologo.entities.Domicilio;
import com.integrador.odontologo.exceptions.EntityNotFoundException;
import com.integrador.odontologo.repository.DomicilioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class DomicilioService {
    private static final Logger logger = LoggerFactory.getLogger(DomicilioService.class);

    @Autowired
    DomicilioRepository repository;

    public List<Domicilio> list(){
        return repository.findAll();
    }

    public Domicilio save(Domicilio domicilio){
        return repository.save(domicilio);
    }

    public void destroy(Long id){
        Optional<Domicilio> domicilio=searchById(id);
        if(domicilio.isPresent())
            repository.deleteById(id);
        else{
            var mensaje= "El DomicilioService cod id= "+ id.toString()+" no existe (destroy)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Domicilio.class, "id", id.toString());
        }
    }

    public Optional<Domicilio> searchById(Long id){
        Optional<Domicilio> domicilio = repository.findById(id);
        if (domicilio.isEmpty()){
            var mensaje= "El DomicilioService cod id= "+ id.toString()+" no existe (searchById)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Domicilio.class,"id", id.toString());
        }
        return domicilio;
    }

    public Domicilio update(Domicilio domicilio) {
        if(searchById(domicilio.getId()).isPresent()) {
            return save(domicilio);
        }else {
            var mensaje= "El DomicilioService cod id= "+ domicilio.getId().toString()+" no existe (update)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Domicilio.class,"id",domicilio.getId().toString());
        }
    }
}
