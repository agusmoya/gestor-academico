package com.utnfrm.microservicios.app.cursos.services;

import com.utnfrm.microservicios.app.cursos.entities.Curso;
import com.utnfrm.microservicios.commons.alumnos.entities.Alumno;
import com.utnfrm.microservicios.commons.services.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CursoService extends CommonService<Curso> {

    Curso findCursoByAlumnoId(Long id) throws Exception;

    List<Long> obtenerExamenesIdsConRespuestasPorAlumnos(Long alumnoId) throws Exception;

    List<Alumno> obtenerAlumnosPorCurso(List<Long> ids) throws Exception;

    void eliminarCursoAlumnoPorId(Long alumnoId) throws Exception;

    List<Curso> listar() throws Exception;

    Page<Curso> listar(Pageable pageable) throws Exception;

    Curso ver(Long id) throws Exception;

    Curso asignarAlumnos(Long cursoId, List<Alumno> alumnos) throws Exception;

    Curso eliminarAlumno(Long cursoId, Alumno alumno) throws Exception;
}
