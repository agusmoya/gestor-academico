package com.utnfrm.microservicios.app.respuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//REMOVIDO: ya que utilizamos mongoDB
//@EntityScan({"com.utnfrm.microservicios.app.respuestas.entities",
////			 "com.utnfrm.microservicios.commons.alumnos.entities",
//			 "com.utnfrm.microservcicios.commons.examenes.entities"})

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosRespuestasApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviciosRespuestasApplication.class, args);
    }

}
