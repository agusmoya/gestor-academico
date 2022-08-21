package com.utnfrm.microservicios.app.respuestas.clients;

import com.utnfrm.microservcicios.commons.examenes.entities.Examen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microservicio-examenes")
public interface ExamenFeignClient {

    @GetMapping("/{id}")
    Examen obtenerExamenPorId(@PathVariable Long id);

    @GetMapping("/respondidos-por-preguntas")
    List<Long> obtenerExamenesIdsPorPreguntaIdRespondidas(@RequestParam List<Long> preguntaIds);

}
