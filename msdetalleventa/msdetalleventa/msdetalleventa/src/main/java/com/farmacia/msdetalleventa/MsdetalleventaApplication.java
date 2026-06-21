package com.farmacia.msdetalleventa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsdetalleventaApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				MsdetalleventaApplication.class,
				args
		);
	}
}