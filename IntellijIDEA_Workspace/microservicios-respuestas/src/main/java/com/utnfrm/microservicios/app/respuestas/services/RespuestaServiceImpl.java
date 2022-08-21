package com.utnfrm.microservicios.app.respuestas.services;

//import com.utnfrm.microservcicios.commons.examenes.entities.Examen;
//import com.utnfrm.microservcicios.commons.examenes.entities.Pregunta;

import com.utnfrm.microservicios.app.respuestas.clients.ExamenFeignClient;
import com.utnfrm.microservicios.app.respuestas.entities.Respuesta;
import com.utnfrm.microservicios.app.respuestas.repositories.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private ExamenFeignClient examenFeignClient;

//    @Override
//    @Transactional
//    public List<Respuesta> saveAll(List<Respuesta> respuestas) throws Exception {
//        try {
//            respuestas = respuestas.stream().map(r -> {
//                r.setAlumnoId(r.getAlumno().getId());
//                return r;
//            }).collect(Collectors.toList());
//            return repository.saveAll(respuestas);
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }

    @Override
    public List<Respuesta> saveAll(List<Respuesta> respuestas) throws Exception {
        try {
            respuestas = respuestas.stream().map(r -> {
                r.setAlumnoId(r.getAlumno().getId());
                r.setPreguntaId(r.getPregunta().getId());
                return r;
            }).collect(Collectors.toList());
            return repository.saveAll(respuestas);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) throws Exception {
//        try {
//            return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }


    //    @Transactional(readOnly = true) Quitamos esto, ya que por defecto mongoDB NO ES TRANSACTIONAL,
//    asique no hace falta aclararlo
    @Override
    public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) throws Exception {
        try {
// COMENTADO: debido a que hacemos una consulta alternativa de mongo, en la cual, luego de comentar @Trancient,
// nos devuelve el objeto solicitado, entonces no tenemos que ir a buscar en otras BDs las relaciones

//            Examen examen = examenFeignClient.obtenerExamenPorId(examenId);
//            List<Pregunta> preguntas = examen.getPreguntas();
//            List<Long> preguntasIds = preguntas.stream()
//                    .map(p -> p.getId())
//                    .collect(Collectors.toList());
//            List<Respuesta> respuestas = repository.findRespuestaByAlumnoByPreguntaIds(alumnoId, preguntasIds);
//
//            respuestas = respuestas.stream()
//                    .map(r -> {
//                        preguntas.forEach(p -> {
//                            if (p.getId().equals(r.getPreguntaId())) {
//                                r.setPregunta(p);
//                            }
//                        });
//                        return r;
//                    }).collect(Collectors.toList());
            List<Respuesta> respuestas = repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
            return respuestas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
//    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<Long> findExamenesIdsRespondidosByAlumno(Long alumnoId) throws Exception {
//        try {
//            return repository.findExamenesIdsRespondidosByAlumno(alumnoId);
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }

    @Override
    public List<Long> findExamenesIdsRespondidosByAlumno(Long alumnoId) throws Exception {
        try {
// COMENTADO: debido a que hacemos una consulta alternativa de mongo, en la cual, luego de comentar @Trancient,
// nos devuelve el objeto solicitado, entonces no tenemos que ir a buscar en otras BDs las relaciones

//            List<Respuesta> respuestasAlumno = repository.findRespuestaByAlumnoId(alumnoId);
//            List<Long> examenesIds = Collections.emptyList();
//
//            if (respuestasAlumno.size() > 0) {
//                List<Long> preguntasIds = respuestasAlumno
//                        .stream()
//                        .map(r -> r.getPreguntaId())
//                        .collect(Collectors.toList());
//
//                examenesIds = examenFeignClient.obtenerExamenesIdsPorPreguntaIdRespondidas(preguntasIds);
//            }
            List<Respuesta> respuestasAlumno = repository.findExamenesIdsRespondidosByAlumno(alumnoId);
            List<Long> examenesIds = respuestasAlumno.stream()
                    .map(r -> r.getPregunta().getExamen().getId())
                    .distinct()
                    .collect(Collectors.toList());
            return examenesIds;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Respuesta> findRespuestaByAlumnoId(Long alumnoId) throws Exception {
        try {
            return repository.findRespuestaByAlumnoId(alumnoId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
