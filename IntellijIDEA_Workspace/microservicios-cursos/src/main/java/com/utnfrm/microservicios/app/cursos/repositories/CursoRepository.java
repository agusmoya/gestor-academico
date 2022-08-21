package com.utnfrm.microservicios.app.cursos.repositories;

import com.utnfrm.microservicios.app.cursos.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Comentado luego de migracion de BD tabla alumnos
//    @Query("SELECT c FROM Curso c JOIN FETCH c.alumnos a WHERE a.id=:id")
//    public Curso findCursoByAlumnoId(@Param("id") Long id);

    @Query("SELECT c FROM Curso c JOIN FETCH c.cursoAlumnos ca WHERE ca.alumnoId=?1")
    public Curso findCursoByAlumnoId(Long id);

    @Modifying
    @Query("delete from CursoAlumno ca where ca.alumnoId=?1")
    public void eliminarCursoAlumnoPorId(Long alumnoId);
}
