package com.utnfrm.microservicios.app.usuarios.controllers;

import com.utnfrm.microservicios.app.usuarios.services.AlumnoService;
import com.utnfrm.microservicios.commons.alumnos.entities.Alumno;
import com.utnfrm.microservicios.commons.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @GetMapping("/alumnos-por-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllById(ids));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @GetMapping("/uploads/img/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(service.verFoto(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @GetMapping("/buscar/{filtro}") // En Postman sería: http://localhost:8090/api/alumnos/buscar/Agus
    public ResponseEntity<?> buscar(@PathVariable String filtro) {
//    @GetMapping("/buscar") // si el nombre de {variable} fuese distinto al del argumento del metodo hay que aclarar @PathVariable(name/value = "variable")
//    public ResponseEntity<?> buscar(@RequestParam String filtro) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findByNombreOrApellido(filtro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        try {
            if (!archivo.isEmpty()) {
                alumno.setFoto(archivo.getBytes());
            }
            return super.crear(alumno, result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @PutMapping("/editar-con-foto/{id}")
    public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo, @PathVariable Long id) throws IOException {
        try {
            if (!archivo.isEmpty()) {
                alumno.setFoto(archivo.getBytes());
            }
            return super.editar(alumno, result, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

}
