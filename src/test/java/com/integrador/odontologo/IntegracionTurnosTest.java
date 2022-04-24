package com.integrador.odontologo;

import com.integrador.odontologo.entities.Domicilio;
import com.integrador.odontologo.entities.Odontologo;
import com.integrador.odontologo.entities.Paciente;
import com.integrador.odontologo.entities.Turno;
import com.integrador.odontologo.service.OdontologoService;
import com.integrador.odontologo.service.PacienteService;
import com.integrador.odontologo.service.TurnoService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    MockMvc mockMvc;

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
    public void listarTurnos() throws Exception {
        //cargar informacion a la BD

        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/api/test/turnos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void listarPacientes() throws Exception {
        //cargar informacion a la BD

        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/api/test/pacientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void listarOdontologos() throws Exception {
        //cargar informacion a la BD

        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/api/test/odontologos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
