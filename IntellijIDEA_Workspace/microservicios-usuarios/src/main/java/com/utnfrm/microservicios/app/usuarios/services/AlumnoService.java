package com.utnfrm.microservicios.app.usuarios.services;

import com.utnfrm.microservicios.commons.alumnos.entities.Alumno;
import com.utnfrm.microservicios.commons.services.CommonService;
import org.springframework.core.io.Resource;

import java.util.List;

public interface AlumnoService extends CommonService<Alumno> {

    List<Alumno> findByNombreOrApellido(String filtro) throws Exception;

    List<Alumno> findAllById(List<Long> ids) throws Exception;

    void eliminarCursoAlumnoPorId(Long id) throws Exception;

    Resource verFoto(Long id) throws Exception;
}
