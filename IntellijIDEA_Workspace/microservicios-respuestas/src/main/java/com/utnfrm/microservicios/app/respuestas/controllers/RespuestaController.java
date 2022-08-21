package com.utnfrm.microservicios.app.respuestas.controllers;

import com.utnfrm.microservicios.app.respuestas.entities.Respuesta;
import com.utnfrm.microservicios.app.respuestas.services.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RespuestaController {

    @Autowired
    protected RespuestaService service;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody List<Respuesta> respuestas) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAll(respuestas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/alumno/{alumnoId}/examen/{examenId}")
    public ResponseEntity<?> obtenerRespuestaPorAlumnoPorExamen(@PathVariable Long alumnoId, @PathVariable Long examenId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findRespuestaByAlumnoByExamen(alumnoId, examenId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos") // esto lo dejamos en null
    public ResponseEntity<?> obtenerExamenesIdsConRespuestasPorAlumnos(@PathVariable Long alumnoId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findExamenesIdsRespondidosByAlumno(alumnoId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"" + e.getMessage() + "\"}");
        }
    }
}
