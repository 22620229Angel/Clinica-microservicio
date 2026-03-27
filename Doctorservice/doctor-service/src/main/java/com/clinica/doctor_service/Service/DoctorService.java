package com.clinica.doctor_service.Service;

import com.clinica.doctor_service.Dto.DoctorDto;
import java.util.List;

public interface DoctorService {
    List<DoctorDto> listarTodos();
    DoctorDto guardar(DoctorDto doctorDto);
    DoctorDto buscarPorId(Long id);
}