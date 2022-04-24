package com.integrador.odontologo.service;

import com.integrador.odontologo.entities.Paciente;
import com.integrador.odontologo.entities.Turno;
import com.integrador.odontologo.exceptions.EntityNotFoundException;
import com.integrador.odontologo.repository.TurnoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TurnoService {
    private static final Logger logger = LoggerFactory.getLogger(TurnoService.class);

    @Autowired
    TurnoRepository repository;


    public List<Turno> list() {
        return repository.findAll();
    }

    public Turno save(Turno turno) {
        return repository.save(turno);
    }

    public Turno update(Turno turno) {
        if (searchById(turno.getId()).isPresent()) {
            return save(turno);
        } else {
            var mensaje= "El turno cod id= "+ turno.getId().toString()+" no existe (update)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Turno.class, "id", turno.getId().toString());

        }
    }

    public Optional<Turno> searchById(Long id) {
        Optional<Turno> turno = repository.findById(id);
        if (!turno.isPresent()) {
            var mensaje= "El turno cod id= "+ id.toString()+" no existe (searchById)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Turno.class, "id", id.toString());
        }
        return turno;
    }

    public void destroy(Long id) {
        Optional<Turno> turno = searchById(id);
        if (turno.isPresent())
            repository.deleteById(id);
        else {
            var mensaje= "El turno cod id= "+ id.toString()+" no existe (destroy)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Turno.class, "id", id.toString());
        }
    }
}





