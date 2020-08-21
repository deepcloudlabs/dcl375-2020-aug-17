package com.example.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.crm.service.CustomerService;

// How to start Mongo server: C:\DEVEL\stage\var\scripts\mongod.bat
// How to start Mongo CLI: C:\DEVEL\stage\var\scripts\mongo-cli.bat

// How to start MySQL server: C:\DEVEL\stage\var\scripts\start-mysql.bat
// How to start MySQL CLI: C:\DEVEL\stage\var\scripts\mysql-cli.bat
@SpringBootApplication
public class CrmRestApiApplication implements ApplicationRunner {
	@Autowired
	private CustomerService customerService;
	
	public static void main(String[] args) {
		SpringApplication.run(CrmRestApiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.err.println(customerService.getClass());
		
	}

}
