package com.utnfrm.microservcicios.commons.examenes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "preguntas")
@Getter
@Setter
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enunciado;
    @JsonIgnoreProperties(value = {"preguntas"}, allowSetters = true)
    // allowSetter: que suprima el atributo preguntas (para evitar loop infinito) pero permita los setters para asignar la relación inversa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examen_id")
    private Examen examen;

    @Override
    public boolean equals(Object obj) {
        // son instancias iguales? (con el this aclaramos que buscamos una instancia de esta clase, Alumnos.
        if (this == obj) {
            return true;
        }
        //si no son instancias iguales, preguntamos si el obj es una instancia de Alumno
        if (!(obj instanceof Pregunta)) {
            return false;
        }
        Pregunta p = (Pregunta) obj;
        return this.id != null && this.id.equals(p.getId()); // el null no se compara con equals porque no "hay nada que comparar", por eso es con != 0 ==
    }
}
