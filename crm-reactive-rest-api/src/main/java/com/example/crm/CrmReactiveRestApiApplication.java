package com.example.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import com.example.crm.repository.CustomerDocumentReactiveRepository;

@SpringBootApplication
// Spring Boot 2-2.2
//@EnableWebFlux 
//@EnableReactiveMongoRepositories
public class CrmReactiveRestApiApplication implements ApplicationRunner {

	@Autowired
	private CustomerDocumentReactiveRepository customerRepo;

	public static void main(String[] args) {
		SpringApplication.run(CrmReactiveRestApiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		customerRepo.findAllFlux(PageRequest.of(0, 20)).subscribe(customer -> {
			System.err.println(Thread.currentThread().getName() + ": " + customer);
		});
		System.err.println("ApplicationRunner has finished!");
	}

}
