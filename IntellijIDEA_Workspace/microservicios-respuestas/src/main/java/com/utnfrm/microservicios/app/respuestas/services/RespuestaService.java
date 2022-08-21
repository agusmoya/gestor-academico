package com.utnfrm.microservicios.app.respuestas.services;

import com.utnfrm.microservicios.app.respuestas.entities.Respuesta;

import java.util.List;

public interface RespuestaService {

    public List<Respuesta> saveAll(List<Respuesta> respuestas) throws Exception;

    public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) throws Exception;

    public List<Long> findExamenesIdsRespondidosByAlumno(Long alumnoId) throws Exception;

    List<Respuesta> findRespuestaByAlumnoId(Long alumnoId) throws Exception;

}
