package com.farmacia.msinventario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsinventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsinventarioApplication.class, args);
	}
}
