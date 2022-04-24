package com.integrador.odontologo;

import com.integrador.odontologo.entities.ERole;
import com.integrador.odontologo.entities.Role;
import com.integrador.odontologo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class OdontologoApplication {

	@Autowired
	RoleRepository repository;

	public static void main(String[] args) {

		SpringApplication.run(OdontologoApplication.class, args);
	}

	public void datoIniciales(){
		Role role = new Role(ERole.ROLE_INVITADO);
		repository.save(role);
	}

}
