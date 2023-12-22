package com.skica.spvbpisvsportalbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpvbpIsvsPortalBackendApplication {

	public static void main(String[] args) {
		System.out.println("JDBCURL" + System.getenv("DB_URL"));
		SpringApplication.run(SpvbpIsvsPortalBackendApplication.class, args);
	}

}
