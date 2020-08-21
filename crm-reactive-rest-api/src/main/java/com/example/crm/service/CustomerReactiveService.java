package com.example.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.domain.CustomerDocument;
import com.example.crm.repository.CustomerDocumentReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerReactiveService {

	@Autowired private CustomerDocumentReactiveRepository customerRepository;
	
	public Mono<CustomerDocument> findCustomerByIdentity(String identity) {
		return customerRepository.findById(identity);
	}

	public Flux<CustomerDocument> findAll(int page, int size) {
		return customerRepository.findAllFlux(PageRequest.of(page, size));
	}

	
}
