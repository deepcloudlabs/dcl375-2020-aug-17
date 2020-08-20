package com.example.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.domain.Customer;
import com.example.crm.service.CustomerService;

@RestController
@RequestScope
@RequestMapping("customers")
@CrossOrigin
// http://localhost:4001/crm/api/v1/customers (1) ✔
public class CustomerController {
	@Autowired private CustomerService customerService;
	
    // GET http://localhost:4001/crm/api/v1/customers/11111111110
	@GetMapping("{identity}")
	public Customer getCustomerByIdentity(@PathVariable String identity) {
		return customerService.findCustomerByIdentity(identity);
	}
	
	// GET http://localhost:4001/crm/api/v1/customers?page=10&size=25
	@GetMapping(params = {"page", "size"})
	public List<Customer> getCustomers(
			@RequestParam(name = "page", required = true, defaultValue = "0") int page, 
			@RequestParam(name = "size", required = true, defaultValue = "10") int size){
		return customerService.findCustomersWithPagination(page, size);
	}
	
	@PostMapping
	public void addCustomer(@RequestBody Customer customer) {
		customerService.createCustomer(customer);
	}
}
