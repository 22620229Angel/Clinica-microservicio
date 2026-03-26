package com.hospital.cita.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pacienteId;
    private Long doctorId;

    private LocalDateTime fechaHora;

    private String motivo;
    private String estado;
    private String consultorio;
    private String observaciones;
}