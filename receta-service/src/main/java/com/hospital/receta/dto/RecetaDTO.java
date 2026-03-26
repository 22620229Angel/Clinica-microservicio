package com.hospital.receta.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RecetaDTO {
    private Long id;
    private String medicamento;
    private String dosis;
    private LocalDate fechaEmision;
    private Long pacienteId;
    // No ponemos las indicaciones largas o campos internos si no son necesarios
}