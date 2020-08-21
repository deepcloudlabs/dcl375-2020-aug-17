package com.example.crm.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.application.CrmApplication;
import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;
import com.example.crm.dto.CustomerRequest;
import com.example.crm.dto.CustomerResponse;
import com.example.validation.TcKimlik;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@RestController
@RequestScope
@RequestMapping("/customers")
@CrossOrigin
@Validated
public class CustomerController {
	@Autowired
	private CrmApplication crmApplication;
	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public CustomerResponse acquireCustomer(@RequestBody @Validated CustomerRequest request) {
		var cust = mapper.map(request, Customer.class);
		crmApplication.acquireCustomer(cust);
		return new CustomerResponse("success");
	}

	@DeleteMapping("{identity}")
	public CustomerResponse fireEmployee(@PathVariable @TcKimlik String identity) {
		var removedCustomer = crmApplication.loseCustomer(TcKimlikNo.of(identity));
		if (removedCustomer.isPresent())
			return new CustomerResponse("success");
		return new CustomerResponse("fail");
	}
}
