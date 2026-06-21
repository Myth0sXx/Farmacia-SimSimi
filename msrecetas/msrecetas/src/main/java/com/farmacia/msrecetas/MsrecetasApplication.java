package com.farmacia.msrecetas;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsrecetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsrecetasApplication.class, args);
	}
}
