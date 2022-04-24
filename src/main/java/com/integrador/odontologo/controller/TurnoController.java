package com.integrador.odontologo.controller;

import com.integrador.odontologo.entities.Turno;
import com.integrador.odontologo.exceptions.EntityNotFoundException;
import com.integrador.odontologo.service.OdontologoService;
import com.integrador.odontologo.service.PacienteService;
import com.integrador.odontologo.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turnos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Turno> turnoList(){
        return turnoService.list();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Turno turnoNew(@RequestBody @Valid Turno turno) throws EntityNotFoundException {
        return turnoService.save(turno);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Turno turnoUpdate(@RequestBody @Valid Turno turno) throws EntityNotFoundException{
        return turnoService.update(turno);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Optional<Turno> turnoById(@PathVariable Long id) throws EntityNotFoundException{
        return turnoService.searchById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<String> destroy(@PathVariable Long id) throws EntityNotFoundException {

        if(turnoService.searchById(id).isPresent()){
            turnoService.destroy(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
