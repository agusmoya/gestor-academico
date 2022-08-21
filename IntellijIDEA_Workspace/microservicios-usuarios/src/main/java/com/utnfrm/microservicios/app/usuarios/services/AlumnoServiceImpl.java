package com.utnfrm.microservicios.app.usuarios.services;

import com.utnfrm.microservicios.app.usuarios.clients.CursoFeignClient;
import com.utnfrm.microservicios.app.usuarios.repsitories.AlumnoRepository;
import com.utnfrm.microservicios.commons.alumnos.entities.Alumno;


import com.utnfrm.microservicios.commons.services.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

    @Autowired
    protected CursoFeignClient cursoFeignClient;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String filtro) throws Exception {
        try {
            return repository.findByNombreOrApellido(filtro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAllById(List<Long> ids) throws Exception {
        try {
            return repository.findAllById(ids);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void eliminarCursoAlumnoPorId(Long id) throws Exception {
        try {
            System.out.println("4) PASO POR AQUI LUEGO DEL SERVICE COMMON!!!");
            cursoFeignClient.eliminarCursoAlumnoPorId(id);
            System.out.println("ESTO NO SE DEBERIA VER!!!");
        } catch (Exception e) {
            System.out.println("5) ESTO SI SE DEBERIA VER!!!");
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws Exception {
        try {
            System.out.println("2) PASO POR EL SERVICIO ALUMNO!!!");
            super.deleteById(id);
            this.eliminarCursoAlumnoPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Resource verFoto(Long id) throws Exception {
        try {
            Optional<Alumno> optionalAlumno = repository.findById(id);
            Alumno alumno = optionalAlumno.get();
            if (alumno.getFoto() == null) {
                throw new Exception();
            }
            Resource imagen = new ByteArrayResource(alumno.getFoto());
            return imagen;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Alumno> findAll() throws Exception {
        try {
            return repository.findAllByOrderByIdAsc();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Alumno> findAll(Pageable pageable) throws Exception {
        try {
            return repository.findAllByOrderByIdAsc(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
