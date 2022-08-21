package com.utnfrm.microservicios.app.respuestas.entities;

import com.utnfrm.microservcicios.commons.examenes.entities.Pregunta;
import com.utnfrm.microservicios.commons.alumnos.entities.Alumno;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


// SE REMOVERAN TODAS LAS ANOTACIONES DE JPA PARA PASAR A UTILIZAR MONGODB - Video 3 - Folder 12
@Document(collection = "respuestas")
@Getter
@Setter
public class Respuesta {

    @Id //Se pasa de Long a String porque en mongoDB el id es ALPHANUMERICO
    private String id;

    private String texto;

    //@Transient
    private Alumno alumno;

    private Long alumnoId;

    //@Transient
    private Pregunta pregunta;

    private Long preguntaId;
}
