package com.utnfrm.microservicios.app.examenes.services;

import com.utnfrm.microservcicios.commons.examenes.entities.Asignatura;
import com.utnfrm.microservcicios.commons.examenes.entities.Examen;
import com.utnfrm.microservcicios.commons.examenes.entities.Pregunta;
import com.utnfrm.microservicios.app.examenes.repositories.AsignaturaRepository;
import com.utnfrm.microservicios.app.examenes.repositories.ExamenRepository;
import com.utnfrm.microservicios.commons.services.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    @Transactional
    public Examen update(Long id, Examen examen) throws Exception {
        try {
            Optional<Examen> examenOptional = repository.findById(id);
            Examen examenBD = examenOptional.get();
            examenBD.setNombre(examen.getNombre());
            List<Pregunta> eliminadas = new ArrayList<>();

//            examenBD.getPreguntas().forEach(pdb -> {
//                if (!examen.getPreguntas().contains(pdb)) {
//                    eliminadas.add(pdb);
//                }
//            });
//
//            eliminadas.forEach(p -> examenBD.removePregunta(p));

            eliminadas = examenBD.getPreguntas()
                    .stream()
                    .filter(pdb -> !examen.getPreguntas().contains(pdb))
                    .collect(Collectors.toList());

            eliminadas.forEach(examenBD::removePregunta);
            examenBD.setPreguntas(examen.getPreguntas());

            // MODIFICAMOS PARA QUE SE ACTUALICE EL FRONT
            examenBD.setAsignaturaHija(examen.getAsignaturaHija());
            examenBD.setAsignaturaPadre(examen.getAsignaturaPadre());

            return repository.save(examen);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String filtro) throws Exception {
        try {
            return repository.findByNombre(filtro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> findAllAsignaturas() throws Exception {
        try {
            return asignaturaRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> findExamenesIdsRespondidosByPreguntaIds(List<Long> preguntaIds) throws Exception {
        try {
            return repository.findExamenesIdsRespondidosByPreguntaIds(preguntaIds);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
