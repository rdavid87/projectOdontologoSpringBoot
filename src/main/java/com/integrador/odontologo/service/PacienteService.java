package com.integrador.odontologo.service;

import com.integrador.odontologo.entities.Paciente;
import com.integrador.odontologo.exceptions.EntityNotFoundException;
import com.integrador.odontologo.repository.PacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService{
    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    @Autowired
    PacienteRepository repository;

    public List<Paciente> list() {
        return repository.findAll();
    }

    public Paciente save(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente update(Paciente paciente) {
        if(searchById(paciente.getId()).isPresent()) {
            return save(paciente);
        }else {
            var mensaje= "El paciente cod id= "+ paciente.getId().toString()+" no existe (update)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Paciente.class, "id", paciente.getId().toString());
        }
    }

    public Optional<Paciente> searchById(Long id) {
        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isEmpty()) {
            var mensaje= "El paciente cod id= "+ id.toString()+" no existe (searchById)";
            logger.info(mensaje);
            throw new EntityNotFoundException(Paciente.class, "id", id.toString());
        }
        return paciente;
    }

    public void destroy(Long id) {
        Optional<Paciente> paciente=searchById(id);
        if(paciente.isPresent())
            repository.deleteById(id);
        else{
            var mensaje= "El paciente cod id= "+ id.toString()+" no existe para (destroy))";
            logger.info(mensaje);
            throw new EntityNotFoundException(Paciente.class, "id", id.toString());
        }
    }
}
