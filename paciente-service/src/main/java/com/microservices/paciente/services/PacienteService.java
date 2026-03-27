package com.microservices.paciente.services;

import com.microservices.paciente.controller.Paciente;
import com.microservices.paciente.reposiitory.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository repositorio;

    public List<Paciente> listarTodos() {
        return repositorio.findAll();
    }

    public Paciente guardar(Paciente paciente) {
        return repositorio.save(paciente);
    }

    public Paciente buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el paciente con ID: " + id));
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}