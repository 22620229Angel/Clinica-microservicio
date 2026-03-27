package com.microservices.paciente.reposiitory;

import com.microservices.paciente.controller.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Puedes añadir búsquedas personalizadas:
    Optional<Paciente> findByCorreo(String correo);
}