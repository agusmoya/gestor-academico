package com.utnfrm.microservicios.app.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({//"com.utnfrm.microservicios.commons.alumnos.entities",
        "com.utnfrm.microservcicios.commons.examenes.entities",
        "com.utnfrm.microservicios.app.cursos.entities"})
public class MicroserviciosCursosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviciosCursosApplication.class, args);
    }

}
