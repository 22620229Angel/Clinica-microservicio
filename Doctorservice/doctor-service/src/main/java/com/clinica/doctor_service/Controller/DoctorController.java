package com.clinica.doctor_service.Controller;

import com.clinica.doctor_service.Model.Doctor;
import com.clinica.doctor_service.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public List<Doctor> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Doctor guardar(@RequestBody Doctor doctor) {
        return repository.save(doctor);
    }
}