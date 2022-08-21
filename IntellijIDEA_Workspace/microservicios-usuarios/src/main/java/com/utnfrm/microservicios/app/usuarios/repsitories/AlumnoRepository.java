package com.utnfrm.microservicios.app.usuarios.repsitories;

import com.utnfrm.microservicios.commons.alumnos.entities.Alumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query(value = "SELECT a FROM Alumno a WHERE upper(a.nombre) LIKE upper(concat('%', :filtro, '%')) OR upper(a.apellido) LIKE upper(concat('%', :filtro, '%'))")
    List<Alumno> findByNombreOrApellido(@Param("filtro") String filtro);

    List<Alumno> findAllByOrderByIdAsc();

    Page<Alumno> findAllByOrderByIdAsc(Pageable pageable);

}
