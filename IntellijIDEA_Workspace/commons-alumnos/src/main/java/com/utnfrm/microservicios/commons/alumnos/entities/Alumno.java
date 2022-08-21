package com.utnfrm.microservicios.commons.alumnos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "alumnos")
@Getter
@Setter
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotEmpty
    private String nombre;
    @Column
    @NotEmpty
    private String apellido;
    @Column
    @NotEmpty
    @Email
    private String email;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Lob
    @JsonIgnore
    private byte[] foto;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Integer getFotoHashCode() {// Todos los metodo get en el Entity se va a generar o serializar como un atributo en el json
        return (this.foto != null) ? this.foto.hashCode() : null; // hashCode() genera un hash o codigo unico para cada instancia heredada de la clase OBJECT
    }

    @Override
    public boolean equals(Object obj) {
        // son instancias iguales? (con el this aclaramos que buscamos una instancia de esta clase, Alumnos.
        if (this == obj) {
            return true;
        }
        //si no son instancias iguales, preguntamos si el obj es una instancia de Alumno
        if (!(obj instanceof Alumno)) {
            return false;
        }
        Alumno a = (Alumno) obj;
        return this.id != null && this.id.equals(a.getId()); // el null no se compara con equals porque no "hay nada que comparar", por eso es con != 0 ==

//        if (this == o) return true;
//        // getClass() != o.getClass() /O/ this instanceOf Alumno
//        if (o == null || getClass() != o.getClass()) return false;
//        Alumno alumno = (Alumno) o;
//        return Objects.equals(id, alumno.id);
    }

}

