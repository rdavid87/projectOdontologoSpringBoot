package com.integrador.odontologo.controller;

import com.integrador.odontologo.entities.Odontologo;
import com.integrador.odontologo.entities.Paciente;
import com.integrador.odontologo.entities.Turno;
import com.integrador.odontologo.exceptions.EntityNotFoundException;
import com.integrador.odontologo.service.OdontologoService;
import com.integrador.odontologo.service.PacienteService;
import com.integrador.odontologo.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping("/turnos")
    public List<Turno> turnoList(){
        return turnoService.list();
    }

    @GetMapping("/pacientes")
    public List<Paciente> pacienteList() throws EntityNotFoundException {
        return pacienteService.list();
    }

    @GetMapping("/odontologos")
    public List<Odontologo> odontologolist() throws EntityNotFoundException{
        return odontologoService.list();
    }
}
