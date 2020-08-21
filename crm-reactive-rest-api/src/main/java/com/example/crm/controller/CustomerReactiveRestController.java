package com.example.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.domain.CustomerDocument;
import com.example.crm.service.CustomerReactiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
@CrossOrigin
public class CustomerReactiveRestController {

	@Autowired
	private CustomerReactiveService customerReactiveService;

	@GetMapping("{identity}")
	public Mono<CustomerDocument> getCustomerByIdentity(@PathVariable String identity) {
		return customerReactiveService.findCustomerByIdentity(identity);
	}

	@GetMapping(params = { "page", "size" })
	public Flux<CustomerDocument> getCustomers(@RequestParam("page") int page, @RequestParam("size") int size) {
		return customerReactiveService.findAll(page, size);
	}

}
