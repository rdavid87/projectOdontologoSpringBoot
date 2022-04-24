package com.integrador.odontologo.controller;


import com.integrador.odontologo.entities.Paciente;
import com.integrador.odontologo.exceptions.EntityNotFoundException;
import com.integrador.odontologo.service.PacienteService;
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
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PacienteController {
    private static final Logger logger = LoggerFactory.getLogger(OdontologoController.class);

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Paciente> pacienteList() throws EntityNotFoundException {
        return pacienteService.list();
    }

    @GetMapping("/view")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Paciente> pacienteView() throws EntityNotFoundException {
        return pacienteService.list();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Paciente pacienteNew(@RequestBody @Valid Paciente paciente) throws EntityNotFoundException{
        return pacienteService.save(paciente);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Paciente pacienteUpdate(@RequestBody @Valid Paciente paciente) throws EntityNotFoundException{
        return pacienteService.update(paciente);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Paciente pacienteById(@PathVariable Long id) throws EntityNotFoundException {
        return pacienteService.searchById(id).get();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Paciente> pacienteDestroy(@PathVariable Long id) throws EntityNotFoundException {

        if(pacienteService.searchById(id).isPresent()){
            pacienteService.destroy(id);
            logger.info("Se eliminó un odontologo con id="+id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }



}
