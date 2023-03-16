package com.v1.microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableFeignClients

public class MicroserviciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosApplication.class, args);
	}

}
