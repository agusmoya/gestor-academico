package com.utnfrm.microservicios.app.respuestas.repositories;

import com.utnfrm.microservicios.app.respuestas.entities.Respuesta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RespuestaRepository extends MongoRepository<Respuesta, String> {

    @Query("{ 'alumnoId': ?0, 'preguntaId': { $in:?1 } }")
    List<Respuesta> findRespuestaByAlumnoByPreguntaIds(Long alumnoId, List<Long> preguntaIds);

    @Query("{ 'alumnoId': ?0 }")
    List<Respuesta> findRespuestaByAlumnoId(Long alumnoId);

    @Query("{ 'alumnoId': ?0, 'pregunta.examen.id': ?1 }")
    List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);

    @Query(value = "{ 'alumnoId': ?0 }", fields = "{ 'pregunta.examen.id': 1 }")
    List<Respuesta> findExamenesIdsRespondidosByAlumno(Long alumnoId);
}

// REEMPLAZAMOS JpaRepository POR MongoRepository
//public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
//
//// RECORDAR: el fetch se usa para traernos los demas objetos relacionados con la entidad en cuestion sin hacer consultas separadas!
//// COMENTADA: porque modificamos la query para la relacion distribuida
////    @Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id=?1 and e.id=?2")
////    public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
//
//    @Query("select r from Respuesta r join fetch r.pregunta p join fetch p.examen e where r.alumnoId=?1 and e.id=?2")
//    public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
//
//// ENTONCES: aquí no usamos fetch ya que solo nos interesa traer el id de examen (la entidad en cuestion) y no los objetos relacionados como puede ser las preguntas
//// COMENTADA: porque modificamos la query para la relacion distribuida
////    @Query("select e.id from Respuesta r join r.alumno a join r.pregunta p join p.examen e where a.id=?1 group by e.id")
////    public List<Long> findExamenesIdsRespondidosByAlumno(Long alumnoId);
//
//    @Query("select e.id from Respuesta r join r.pregunta p join p.examen e where r.alumnoId=?1 group by e.id")
//    public List<Long> findExamenesIdsRespondidosByAlumno(Long alumnoId);
//
//}