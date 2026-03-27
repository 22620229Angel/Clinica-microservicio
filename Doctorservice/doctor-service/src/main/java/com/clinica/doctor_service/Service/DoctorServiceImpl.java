package com.clinica.doctor_service.Service;

import com.clinica.doctor_service.Dto.DoctorDto;
import com.clinica.doctor_service.Model.Doctor;
import com.clinica.doctor_service.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Override
    public List<DoctorDto> listarTodos() {
        return repository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto guardar(DoctorDto dto) {
        Doctor doctor = convertirAEntidad(dto);
        Doctor guardado = repository.save(doctor);
        return convertirADto(guardado);
    }

    @Override
    public DoctorDto buscarPorId(Long id) {
        Doctor doctor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
        return convertirADto(doctor);
    }

    // Métodos auxiliares de mapeo (Manual por ahora)
    private DoctorDto convertirADto(Doctor doctor) {
        return new DoctorDto(doctor.getId(), doctor.getNombre(), 
                            doctor.getEspecialidad(), doctor.getConsultorio());
    }

    private Doctor convertirAEntidad(DoctorDto dto) {
        Doctor doctor = new Doctor();
        doctor.setNombre(dto.getNombre());
        doctor.setEspecialidad(dto.getEspecialidad());
        doctor.setConsultorio(dto.getConsultorio());
        return doctor;
    }
}
