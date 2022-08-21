package com.utnfrm.microservcicios.commons.examenes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "examenes")
@Getter
@Setter
public class Examen {
    // ALL = PERSIST + REMOVE -> SI borro un examen se borran las preguntas asociadas a el
    // orphanRemoval = true: si borro una pregunta del List preguntas, la columna "examen_id" (foreign key) quedará en null. ESto elimina todas las preguntas con FK null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 20)
    private String nombre;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @JsonIgnoreProperties(value = {"examen"})
    @OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas = new ArrayList<>();

    @JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Asignatura asignaturaHija;

    @JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Asignatura asignaturaPadre;

    @Transient
    private boolean respondido;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas.clear(); // Es la forma adecuada de reiniciar el listado de preguntas y no a través de un new ArrayList<>(). Siempre inicializar el ArrayList una sola vez
        for (Pregunta p : preguntas) {
            this.addPregunta(p);
        }
        //preguntas.forEach(p -> {
        //   this.addPregunta(p);
        // });
        //==
        //preguntas.forEach(p -> this.addPregunta(p));
        //==
        //preguntas.forEach(this::addPregunta); // ya que resivimos un argumento solo, de esta manera se asume lo que se recibe como p tambien se pasa como p
    }

    public void addPregunta(Pregunta pregunta) {
        this.preguntas.add(pregunta);
        pregunta.setExamen(this); // asignarmos la serlacion inversa. Para que se guarde en FK "examen_id" el id correspondiente y no quede en null
    }

    public void removePregunta(Pregunta pregunta) {
        this.preguntas.remove(pregunta);
        pregunta.setExamen(null); // aqui el orphan removal hace su trabajo
    }

    @Override
    public boolean equals(Object obj) {
        // son instancias iguales? (con el this aclaramos que buscamos una instancia de esta clase, Alumnos.
        if (this == obj) {
            return true;
        }
        //si no son instancias iguales, preguntamos si el obj es una instancia de Alumno
        if (!(obj instanceof Examen)) {
            return false;
        }
        Examen e = (Examen) obj;
        return this.id != null && this.id.equals(e.getId()); // el null no se compara con equals porque no "hay nada que comparar", por eso es con != 0 ==
    }
}
