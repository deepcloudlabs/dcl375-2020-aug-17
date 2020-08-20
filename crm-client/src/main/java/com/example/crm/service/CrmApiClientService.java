package com.example.crm.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.crm.domain.Customer;

@SuppressWarnings("deprecation")
@Service
public class CrmApiClientService {
	private static final String CRM_REST_URL = "http://localhost:4001/crm/api/v1/customers";
	
	@Scheduled(fixedRate = 10)
	public void syncCall() {
		var restTemplate = new RestTemplate(); // call -> 1sec 10xcall -> 10sec
		var firstPage = restTemplate.getForEntity( // blocking
				CRM_REST_URL.concat("?page=0&size=10"), 
				Customer[].class).getBody();
		System.err.println(Thread.currentThread().getName());
		for (var customer : firstPage)
			System.err.println("syncCall: "+customer);
	}
	
	@Scheduled(fixedRate = 10)
	public void asyncCall() {
		var restTemplate = new AsyncRestTemplate(); // call -> 1sec 10xcall -> ~1sec
		var firstPage = restTemplate.getForEntity( // non-blocking
				CRM_REST_URL.concat("?page=0&size=10"), 
				Customer[].class);
		firstPage.addCallback((responseEntity) -> { // event-driven
			System.err.println(Thread.currentThread().getName());
			for (var customer : responseEntity.getBody())
				System.err.println("asyncCall: "+customer);			
		},(error) -> {
			System.err.println(error.getMessage());	
		});
	}
}
