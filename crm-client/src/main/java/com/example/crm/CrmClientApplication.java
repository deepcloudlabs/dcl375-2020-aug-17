package com.example.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrmClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmClientApplication.class, args);
	}

}
