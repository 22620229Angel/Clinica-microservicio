package com.clinica.doctor_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.doctor_service.Model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}