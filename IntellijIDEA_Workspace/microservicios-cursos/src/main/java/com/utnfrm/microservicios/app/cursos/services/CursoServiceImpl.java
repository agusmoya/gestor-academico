package com.utnfrm.microservicios.app.cursos.services;

import com.utnfrm.microservcicios.commons.examenes.entities.Examen;
import com.utnfrm.microservicios.app.cursos.clients.AlumnoFeignClient;
import com.utnfrm.microservicios.app.cursos.clients.RespuestaFeignClient;
import com.utnfrm.microservicios.app.cursos.entities.Curso;
import com.utnfrm.microservicios.app.cursos.entities.CursoAlumno;
import com.utnfrm.microservicios.app.cursos.repositories.CursoRepository;
import com.utnfrm.microservicios.commons.alumnos.entities.Alumno;
import com.utnfrm.microservicios.commons.services.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    @Autowired
    RespuestaFeignClient respuestaFeignClient;

    @Autowired
    AlumnoFeignClient alumnoFeignClient;

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) throws Exception {
        try {
            Curso curso = repository.findCursoByAlumnoId(id);

            if (curso != null) {

                //ATENCION: no realizar llamado a otro microservicio dentro de un bucle. Hacerlo una sola vez y
                // traer varios elementos en una sola consulta. Por temas de performance
                List<Long> examenesIds = this.obtenerExamenesIdsConRespuestasPorAlumnos(id);
                if (examenesIds != null && examenesIds.size() > 0) {
                    //API Stream (parte del core de Java desde la v. 8): nos permite manipular los objetos, Listas o Colecciones
                    // como un flujo, utilizando operadores. X ej el map: que nos permite tomar un flujo de una coelccion de objs, y x cada obj
                    // realizar un cambio (cambiar su estado). Ahora lo que necesitamos es cambiar el status del examen a Respondido en el caso
                    // de que se encuentre dentro de esta lista de exemanes Ids.
                    // Tener en cuenta que no cambia el estado original de los examenes que tenemos en el curso, sino que se crea una nueva lista
                    // con estos cambios. Por eso es que a continuacion declaramos una nueva lista:

                    List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
                        if (examenesIds.contains(examen.getId())) {
                            examen.setRespondido(true);
                        }
                        return examen;
                    }).collect(Collectors.toList());

                    curso.setExamenes(examenes);
                }
            }

            return curso;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Long> obtenerExamenesIdsConRespuestasPorAlumnos(Long alumnoId) throws Exception {
        try {
            return respuestaFeignClient.obtenerExamenesIdsConRespuestasPorAlumnos(alumnoId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Alumno> obtenerAlumnosPorCurso(List<Long> ids) throws Exception {
        try {
            return alumnoFeignClient.obtenerAlumnosPorCurso(ids);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarCursoAlumnoPorId(Long alumnoId) throws Exception {
        try {
            repository.eliminarCursoAlumnoPorId(alumnoId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Curso> listar() throws Exception {
        try {
            List<Curso> cursos = this.repository.findAll().stream().map(c -> {
                c.getCursoAlumnos().forEach(ca -> {
                    Alumno alumno = new Alumno();
                    alumno.setId(ca.getAlumnoId());
                    c.addAlumno(alumno);
                });
                return c;
            }).collect(Collectors.toList());
            return cursos;
        } catch (Exception var2) {
            throw new Exception(var2.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Curso> listar(Pageable pageable) throws Exception {
        try {
            Page<Curso> cursos = this.repository.findAll(pageable)
                    .map(curso -> {
                        curso.getCursoAlumnos().forEach(ca -> {
                            Alumno alumno = new Alumno();
                            alumno.setId(ca.getAlumnoId());
                            curso.addAlumno(alumno);
                        });
                        return curso;
                    });
            return cursos;
        } catch (Exception var3) {
            throw new Exception(var3.getMessage());
        }
    }

    @Override
    public Curso ver(Long id) throws Exception {
        try {
            Curso curso = this.findById(id);
            if (!curso.getCursoAlumnos().isEmpty()) {
                List<Long> ids = curso.getCursoAlumnos()
                        .stream()
                        .map(ca -> ca.getAlumnoId())
                        .collect(Collectors.toList());
                List<Alumno> alumnos = this.obtenerAlumnosPorCurso(ids);
                curso.setAlumnos(alumnos);
            }
            return curso;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Curso asignarAlumnos(Long cursoId, List<Alumno> alumnos) throws Exception {
        try {
            Curso curso = super.findById(cursoId);

            alumnos.forEach(alumnoAux -> {
                CursoAlumno cursoAlumno = new CursoAlumno();
                cursoAlumno.setAlumnoId(alumnoAux.getId());
                cursoAlumno.setCurso(curso);
                curso.addCursoAlumno(cursoAlumno);
            });

            return super.save(curso);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Curso eliminarAlumno(Long cursoId, Alumno alumno) throws Exception {
        try {
            Curso curso = super.findById(cursoId);
            CursoAlumno cursoAlumno = new CursoAlumno();
            cursoAlumno.setAlumnoId(alumno.getId());
            curso.removeCursoAlumno(cursoAlumno);
            return super.save(curso);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
