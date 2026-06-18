package com.farmacia.msreportes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsreportesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsreportesApplication.class, args);
	}

}

