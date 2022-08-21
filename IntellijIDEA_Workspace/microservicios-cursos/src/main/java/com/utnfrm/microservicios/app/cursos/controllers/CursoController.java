package com.utnfrm.microservicios.app.cursos.controllers;

import com.utnfrm.microservcicios.commons.examenes.entities.Examen;
import com.utnfrm.microservicios.app.cursos.entities.Curso;
import com.utnfrm.microservicios.app.cursos.entities.CursoAlumno;
import com.utnfrm.microservicios.app.cursos.services.CursoService;
import com.utnfrm.microservicios.commons.alumnos.entities.Alumno;
import com.utnfrm.microservicios.commons.controllers.CommonController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    @GetMapping("/balanceador-test")
    public ResponseEntity<?> balanceadorTest() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("balanceador", balanceadorTest);
            response.put("cursos", service.findAll());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.listar());
        } catch (Exception var2) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @Override
    @GetMapping({"/paginable"})
    public ResponseEntity<?> listar(Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.listar(pageable));
        } catch (Exception var3) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @Override
    @GetMapping({"/{id}"})
    public ResponseEntity<?> ver(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.ver(id));
        } catch (Exception var3) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @DeleteMapping("/eliminar-alumno/{id}")
    public ResponseEntity<?> eliminarCursoAlumnoPorId(@PathVariable Long id) {
        try {
            service.eliminarCursoAlumnoPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception var3) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

// Metodo usado antes de migrar alumnos a postgresql
//    @PutMapping("/{id}/asignar-alumnos")
//    public ResponseEntity<?> asignarAlumnos(@PathVariable Long id, @RequestBody List<Alumno> alumnos) {
//        try {
//            Curso curso = this.service.findById(id);
//            if (curso == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//            for (Alumno alumnoAux : alumnos) {
//                curso.addAlumno(alumnoAux);
//            }
////            ===
////            alumnos.forEach(alumnoAux -> {
////                curso.addAlumno(alumnoAux);
////            });
//            return ResponseEntity.status(HttpStatus.CREATED).body(this.service.update(id, curso));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
//        }
//    }

    @PutMapping("/{cursoId}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@PathVariable Long cursoId, @RequestBody List<Alumno> alumnos) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.service.asignarAlumnos(cursoId, alumnos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    // Metodo usado antes de migrar alumnos a postgresql
    //    @PutMapping("/{id}/eliminar-alumno")
    //    public ResponseEntity<?> eliminarAlumno(@PathVariable Long id, @RequestBody Alumno alumno) { // IMPORTANTE ENVIAR ALUMNO CON ID PORQUE EL EQUALS UTILIZA ESTE ATRIBUTO PARA EVALUAR IGUALDAD
    //        try {
    //            Curso curso = this.service.findById(id);
    //            if (curso == null) {
    //                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //            }
    //            curso.removeAlumno(alumno);
    //            return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.save(curso));
    //        } catch (Exception e) {
    //            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
    //        }
    //    }

    @PutMapping("/{cursoId}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Long cursoId, @RequestBody Alumno alumno) { // IMPORTANTE ENVIAR ALUMNO CON ID PORQUE EL EQUALS UTILIZA ESTE ATRIBUTO PARA EVALUAR IGUALDAD
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.eliminarAlumno(cursoId, alumno));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarCursoPorAlumnoId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.findCursoByAlumnoId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamenes(@PathVariable Long id, @RequestBody List<Examen> examenes) {
        try {
            Curso curso = this.service.findById(id);
            if (curso == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            for (Examen examenAux : examenes) {
                curso.addExamen(examenAux);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(curso));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@PathVariable Long id, @RequestBody Examen examen) { // IMPORTANTE ENVIAR ALUMNO CON ID PORQUE EL EQUALS UTILIZA ESTE ATRIBUTO PARA EVALUAR IGUALDAD
        try {
            Curso curso = this.service.findById(id);
            if (curso == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            curso.removeExamen(examen);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.save(curso));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

}
