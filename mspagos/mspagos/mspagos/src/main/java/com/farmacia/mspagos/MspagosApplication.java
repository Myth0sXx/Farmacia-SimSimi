package com.farmacia.mspagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MspagosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MspagosApplication.class, args);
	}

}
