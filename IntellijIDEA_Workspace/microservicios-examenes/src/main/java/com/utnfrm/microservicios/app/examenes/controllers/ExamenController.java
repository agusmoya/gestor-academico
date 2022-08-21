package com.utnfrm.microservicios.app.examenes.controllers;

import com.utnfrm.microservcicios.commons.examenes.entities.Examen;
import com.utnfrm.microservicios.app.examenes.services.ExamenService;
import com.utnfrm.microservicios.commons.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {


    @GetMapping("/respondidos-por-preguntas")
    public ResponseEntity<?> obtenerExamenesIdsPorPreguntaIdRespondidas(@RequestParam List<Long> preguntaIds) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findExamenesIdsRespondidosByPreguntaIds(preguntaIds));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"" + e.getMessage() + "\"}");
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> editar(@Valid @PathVariable Long id, @RequestBody Examen examen, BindingResult result) {
//        try {
//            if (result.hasErrors()) {
//                return this.validar(result);
//            }
//            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, examen));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"" + e.getMessage() + "\"}");
//        }
//    }

    @GetMapping("/buscar/{filtro}")
    public ResponseEntity<?> buscar(@PathVariable String filtro) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findByNombre(filtro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllAsignaturas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"" + e.getMessage() + "\"}");
        }
    }

}
