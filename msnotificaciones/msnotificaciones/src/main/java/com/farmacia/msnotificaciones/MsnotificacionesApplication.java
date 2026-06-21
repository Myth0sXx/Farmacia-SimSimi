package com.farmacia.msnotificaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsnotificacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsnotificacionesApplication.class, args);
	}

}
