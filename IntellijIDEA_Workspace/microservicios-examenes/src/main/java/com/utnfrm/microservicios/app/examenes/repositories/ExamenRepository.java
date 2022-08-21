package com.utnfrm.microservicios.app.examenes.repositories;

import com.utnfrm.microservcicios.commons.examenes.entities.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    @Query("select e from Examen e where e.nombre like %:filtro%")
    List<Examen> findByNombre(@Param("filtro") String filtro);

    @Query("select e.id from Pregunta p join p.examen e where p.id in ?1 group by e.id")
    List<Long> findExamenesIdsRespondidosByPreguntaIds(List<Long> preguntaIds);

}
