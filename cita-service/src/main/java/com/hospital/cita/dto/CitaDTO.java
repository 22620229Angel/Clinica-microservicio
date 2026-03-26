package com.hospital.cita.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaDTO {

    private Long id;
    private Long pacienteId;
    private Long doctorId;
    private LocalDateTime fechaHora;
    private String motivo;
    private String estado;
    private String consultorio;
    private String observaciones;
}