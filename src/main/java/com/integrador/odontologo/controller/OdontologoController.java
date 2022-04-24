package com.integrador.odontologo.controller;

import com.integrador.odontologo.entities.Odontologo;
import com.integrador.odontologo.exceptions.EntityNotFoundException;
import com.integrador.odontologo.service.OdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/odontologos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OdontologoController {
    private static final Logger logger = LoggerFactory.getLogger(OdontologoController.class);

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Odontologo> odontologolist() throws EntityNotFoundException{
        logger.info("Llama a listar odontologos");
        return odontologoService.list();
    }

    @GetMapping("/view")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Odontologo> odontologoView() throws EntityNotFoundException{
        logger.info("Llama a listar odontologos");
        return odontologoService.list();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Odontologo odontologoNew(@RequestBody @Valid Odontologo odontologo) throws EntityNotFoundException{
        return odontologoService.save(odontologo);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Odontologo odontologoUpdate(@RequestBody @Valid Odontologo odontologo) throws EntityNotFoundException{
        return odontologoService.update(odontologo);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Odontologo odontologoById(@PathVariable Long id) throws EntityNotFoundException{
        return odontologoService.searchById(id).get();

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> odontologoDestroy(@PathVariable Long id) throws EntityNotFoundException {
        if(odontologoService.searchById(id).isPresent()){
            odontologoService.destroy(id);
            logger.info("Se eliminó un odontologo con id="+id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            logger.info("No se eliminó un odontologo con id="+id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
