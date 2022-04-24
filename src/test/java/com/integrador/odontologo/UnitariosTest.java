package com.integrador.odontologo;

import com.integrador.odontologo.entities.Domicilio;
import com.integrador.odontologo.entities.Odontologo;
import com.integrador.odontologo.entities.Paciente;
import com.integrador.odontologo.entities.Turno;
import com.integrador.odontologo.service.OdontologoService;
import com.integrador.odontologo.service.PacienteService;
import com.integrador.odontologo.service.TurnoService;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UnitariosTest {

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    TurnoService turnoService;

    @DisplayName("Datos Iniciales")
    @BeforeEach
    void init() {
        Domicilio domicilio = new Domicilio("Primavera", 1,"Mansión", "Reyes");
        Paciente paciente= new Paciente("Valencia","David", "valencia@david.com",99888, LocalDate.of(2022,4,24), domicilio);
        pacienteService.save(paciente);

        Odontologo odontologo = new Odontologo("Gaby", "Presidenta", "9324324");
        odontologoService.save(odontologo);

        Turno turno = new Turno(paciente,odontologo,LocalDate.of(2022,4,24));
        turnoService.save(turno);
    }

    @Test
    void getPacientePorId(){
        Optional<Paciente> paciente = pacienteService.searchById(1L);
        Assert.assertTrue(paciente.get().getId().longValue()>=1L);
    }

    @Test
    void getOdontologoPorId(){
        Optional<Odontologo> odontologo = odontologoService.searchById(1L);
        Assert.assertTrue(odontologo.get().getId().longValue()>=1L);
    }

    @Test
    void getTurnoPorId(){
        Optional<Turno> turno = turnoService.searchById(1L);
        Assert.assertTrue(turno.get().getId().longValue()>=1L);
    }

    @Test
    void getPacientesList(){
        List<Paciente> pacientes = pacienteService.list();
        Assert.assertFalse(pacientes.isEmpty());
    }

    @Test
    void getOdontologosList(){
        List<Odontologo> odontologos = odontologoService.list();
        Assert.assertFalse(odontologos.isEmpty());
    }

    @Test
    void getTurnosList(){
        List<Turno> turnos = turnoService.list();
        Assert.assertFalse(turnos.isEmpty());
    }

    @Test
    void updatePacienteName(){
        Optional<Paciente> paciente = pacienteService.searchById(1L);
        paciente.get().setNombre("Jonas");
        Paciente paciente1=pacienteService.update(paciente.get());
        Assert.assertEquals(paciente1.getNombre(),"Jonas");
    }

    @Test
    void updateOdontologoName(){
        Optional<Odontologo> odontologo = odontologoService.searchById(1L);
        odontologo.get().setNombre("Jonas");
        Odontologo odontologo1=odontologoService.update(odontologo.get());
        Assert.assertEquals(odontologo1.getNombre(),"Jonas");
    }

    @Test
    void updateTurnoFecha(){
        Optional<Turno> turno = turnoService.searchById(1L);
        turno.get().setFecha(LocalDate.of(2022,4,30));
        Turno turno1=turnoService.update(turno.get());
        Assert.assertEquals(turno1.getFecha(),LocalDate.of(2022,4,30));
    }


}
