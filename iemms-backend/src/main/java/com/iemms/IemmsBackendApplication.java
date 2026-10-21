package com.iemms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IemmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IemmsBackendApplication.class, args);
		System.out.println(
			    "JWT_SECRET available: " +
			    (System.getenv("JWT_SECRET") != null)
			);
	}

}
