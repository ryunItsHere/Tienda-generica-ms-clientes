package com.tiendagenerica.ms_clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan(basePackages = "com.tiendagenerica")
public class MsClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				MsClientesApplication.class, args);
	}
}