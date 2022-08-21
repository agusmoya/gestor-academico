package com.utnfrm.microservicios.app.examenes.repositories;

import com.utnfrm.microservcicios.commons.examenes.entities.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
}
