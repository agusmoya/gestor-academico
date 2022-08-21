package com.utnfrm.microservicios.app.cursos.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cursos_alumnos")
@Getter
@Setter
public class CursoAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alumno_id", unique = true)
    private Long alumnoId;

    @JsonIgnoreProperties(value = {"cursoAlumnos"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Override
    public boolean equals(Object obj) {
        // son instancias iguales? (con el this aclaramos que buscamos una instancia de esta clase, Alumnos.
        if (this == obj) {
            return true;
        }
        //si no son instancias iguales, preguntamos si el obj es una instancia de Alumno
        if (!(obj instanceof CursoAlumno)) {
            return false;
        }
        CursoAlumno a = (CursoAlumno) obj;
        return this.alumnoId != null && this.alumnoId.equals(a.getAlumnoId()); // el null no se compara con equals porque no "hay nada que comparar", por eso es con != 0 ==
    }

}
