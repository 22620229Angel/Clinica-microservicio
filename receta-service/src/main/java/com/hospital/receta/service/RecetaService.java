package com.hospital.receta.service;

import com.hospital.receta.model.Receta;
import com.hospital.receta.repository.RecetaRepository;
import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;

    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public List<Receta> obtenerTodas() {
        return recetaRepository.findAll();
    }

    public Receta obtenerPorId(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con id: " + id));
    }

    public Receta guardar(Receta receta) {
        // VALIDACIÓN: Evitar que falten IDs críticos
        if (receta.getPacienteId() == null || receta.getDoctorId() == null) {
            throw new IllegalArgumentException("El ID del paciente y del doctor son obligatorios.");
        }

        // AUTOMATIZACIÓN: Si no mandan fecha, ponemos la de hoy
        if (receta.getFechaEmision() == null) {
            receta.setFechaEmision(LocalDate.now());
        }

        return recetaRepository.save(receta);
    }

    public Receta actualizar(Long id, Receta recetaActualizada) {
        Receta receta = obtenerPorId(id);
        receta.setMedicamento(recetaActualizada.getMedicamento());
        receta.setDosis(recetaActualizada.getDosis());
        receta.setIndicaciones(recetaActualizada.getIndicaciones());
        receta.setFechaEmision(recetaActualizada.getFechaEmision());
        receta.setFechaVencimiento(recetaActualizada.getFechaVencimiento());
        receta.setPacienteId(recetaActualizada.getPacienteId());
        receta.setDoctorId(recetaActualizada.getDoctorId());
        return recetaRepository.save(receta);
    }

    public void eliminar(Long id) {
        // Mejoramos el eliminar: verificamos si existe antes de intentar borrar
        if (!recetaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Receta no encontrada con id: " + id);
        }
        recetaRepository.deleteById(id);
    }

    public List<Receta> obtenerPorPaciente(Long pacienteId) {
        // VALIDACIÓN: No buscar si el ID es nulo
        if (pacienteId == null) {
            throw new IllegalArgumentException("El ID del paciente no puede ser nulo.");
        }
        return recetaRepository.findByPacienteId(pacienteId);
    }
}