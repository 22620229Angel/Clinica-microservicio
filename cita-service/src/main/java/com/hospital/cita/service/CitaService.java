package com.hospital.cita.service;

import com.hospital.cita.dto.CitaDTO;
import com.hospital.cita.model.Cita;
import com.hospital.cita.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CitaService {

    private final CitaRepository citaRepository;

    @Transactional(readOnly = true)
    public List<CitaDTO> obtenerTodas() {
        return citaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CitaDTO obtenerPorId(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));

        return toDTO(cita);
    }

    public CitaDTO crear(CitaDTO dto) {
        Cita cita = toEntity(dto);
        cita.setId(null);

        Cita guardada = citaRepository.save(cita);
        return toDTO(guardada);
    }

    public CitaDTO actualizar(Long id, CitaDTO dto) {
        Cita citaExistente = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));

        citaExistente.setPacienteId(dto.getPacienteId());
        citaExistente.setDoctorId(dto.getDoctorId());
        citaExistente.setFechaHora(dto.getFechaHora());
        citaExistente.setMotivo(dto.getMotivo());
        citaExistente.setEstado(dto.getEstado());
        citaExistente.setConsultorio(dto.getConsultorio());
        citaExistente.setObservaciones(dto.getObservaciones());

        Cita actualizada = citaRepository.save(citaExistente);
        return toDTO(actualizada);
    }

    public void eliminar(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));

        citaRepository.delete(cita);
    }

    @Transactional(readOnly = true)
    public List<CitaDTO> obtenerPorPacienteId(Long pacienteId) {
        return citaRepository.findByPacienteId(pacienteId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CitaDTO> obtenerPorDoctorId(Long doctorId) {
        return citaRepository.findByDoctorId(doctorId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private CitaDTO toDTO(Cita cita) {
        return CitaDTO.builder()
                .id(cita.getId())
                .pacienteId(cita.getPacienteId())
                .doctorId(cita.getDoctorId())
                .fechaHora(cita.getFechaHora())
                .motivo(cita.getMotivo())
                .estado(cita.getEstado())
                .consultorio(cita.getConsultorio())
                .observaciones(cita.getObservaciones())
                .build();
    }

    private Cita toEntity(CitaDTO dto) {
        return Cita.builder()
                .id(dto.getId())
                .pacienteId(dto.getPacienteId())
                .doctorId(dto.getDoctorId())
                .fechaHora(dto.getFechaHora())
                .motivo(dto.getMotivo())
                .estado(dto.getEstado())
                .consultorio(dto.getConsultorio())
                .observaciones(dto.getObservaciones())
                .build();
    }
}