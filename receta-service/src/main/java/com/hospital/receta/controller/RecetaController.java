package com.hospital.receta.controller;

import com.hospital.receta.model.Receta;
import com.hospital.receta.service.RecetaService;
import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping
    public ResponseEntity<List<Receta>> obtenerTodas() {
        return ResponseEntity.ok(recetaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Receta> crear(@RequestBody Receta receta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recetaService.guardar(receta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizar(@PathVariable Long id, @RequestBody Receta receta) {
        return ResponseEntity.ok(recetaService.actualizar(id, receta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recetaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Receta>> obtenerPorPaciente(@PathVariable Long pacienteId) {
        // Usamos el service, NO la clase Repository directamente
        return ResponseEntity.ok(recetaService.obtenerPorPaciente(pacienteId));
    }
}

