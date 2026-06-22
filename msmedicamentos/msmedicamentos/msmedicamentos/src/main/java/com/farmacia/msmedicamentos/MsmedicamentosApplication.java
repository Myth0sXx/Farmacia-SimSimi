package com.farmacia.msmedicamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // <-- Este es el import que faltaba

@EnableDiscoveryClient
@SpringBootApplication
public class MsmedicamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsmedicamentosApplication.class, args);
	}

}