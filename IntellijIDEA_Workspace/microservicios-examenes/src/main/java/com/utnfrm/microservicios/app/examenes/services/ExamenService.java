package com.utnfrm.microservicios.app.examenes.services;

import com.utnfrm.microservcicios.commons.examenes.entities.Asignatura;
import com.utnfrm.microservcicios.commons.examenes.entities.Examen;
import com.utnfrm.microservicios.commons.services.CommonService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamenService extends CommonService<Examen> {

    List<Examen> findByNombre(@Param("filtro") String filtro) throws Exception;

    List<Asignatura> findAllAsignaturas() throws Exception;

    List<Long> findExamenesIdsRespondidosByPreguntaIds(List<Long> preguntaIds) throws Exception;
}
