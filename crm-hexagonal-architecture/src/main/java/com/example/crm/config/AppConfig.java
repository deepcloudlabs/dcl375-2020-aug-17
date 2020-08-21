package com.example.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.crm.application.CrmApplication;
import com.example.crm.application.business.SimpleCrmApplication;
import com.example.crm.infrastructure.EventPushlisher;
import com.example.crm.repository.CustomerRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Configuration
public class AppConfig {

	@Bean
	public CrmApplication employeeApplication(CustomerRepository customerRepository, EventPushlisher eventPushlisher) {
		var crmApplication = new SimpleCrmApplication();
		crmApplication.setCustomerRepository(customerRepository);
		crmApplication.setEventPushlisher(eventPushlisher);
		return crmApplication;
	}
}
