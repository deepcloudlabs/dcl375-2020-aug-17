package com.example.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.domain.Customer;
import com.example.crm.dto.RestErrorMessage;
import com.example.crm.service.CustomerService;

// Rest API Documentation:
// 1. Swagger UI (Smart Bear), Soap UI, Open API v3
// 2. RAML (https://raml.org)
// 3. Spring REST Doc: Test -> (PDF, Html)

@RestController
@RequestScope
@RequestMapping("customers")
@CrossOrigin
// http://localhost:4001/crm/api/v1/customers (1) ✔
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	// GET http://localhost:4001/crm/api/v1/customers/11111111110
	@GetMapping("{identity}")
	public Customer getCustomerByIdentity(@PathVariable String identity) {
		return customerService.findCustomerByIdentity(identity);
	}

	// GET http://localhost:4001/crm/api/v1/customers?page=10&size=25
	@GetMapping(params = { "page", "size" })
	public List<Customer> getCustomers(@RequestParam(name = "page", required = true, defaultValue = "0") int page,
			@RequestParam(name = "size", required = true, defaultValue = "10") int size) {
		return customerService.findCustomersWithPagination(page, size);
	}

	@PostMapping
	public void addCustomer(@RequestBody Customer customer) {
		customerService.createCustomer(customer);
	}

	@PutMapping
	public void updateCustomer(@RequestBody Customer customer) {
		customerService.updateCustomer(customer);
	}

	@PatchMapping("{identity}")
	public void patchCustomer(@PathVariable String identity, @RequestBody Map<String, Object> patchValues) {
		customerService.patchCustomer(identity, patchValues);
	}
	
	@DeleteMapping("{identity}")
	public Customer deleteCustomerByIdentity(@PathVariable String identity) {
		return customerService.removeCustomerByIdentity(identity);
	}
	
}
