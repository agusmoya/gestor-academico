package com.utnfrm.microservcicios.commons.examenes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "asignaturas")
@Getter
@Setter
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @JsonIgnoreProperties(value = {"asignaturasHijas", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Asignatura asignaturaPadre;

    @JsonIgnoreProperties(value = {"asignaturaPadre", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "asignaturaPadre", cascade = CascadeType.ALL)
    private List<Asignatura> asignaturasHijas= new ArrayList<>();;

//    public Asignatura() {
//        asignaturasHijas = new ArrayList<>();
//    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
}
