package com.example.crm.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crm.domain.Customer;
import com.example.crm.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
	
	public Customer findCustomerByIdentity(String identity) {
		Supplier<IllegalArgumentException> illegalArgumentException = () -> new IllegalArgumentException("Cannot find customer with identity "+identity);
		return customerRepository.findById(identity)
				                 .orElseThrow(illegalArgumentException);
	}

	public List<Customer> findCustomersWithPagination(int page, int size) {
		return customerRepository.findAll(PageRequest.of(page, size)).getContent();
	}

	@Transactional
	public void createCustomer(Customer customer) {
		var identity = customer.getIdentity();
		if( customerRepository.findById(identity).isPresent())
			throw new IllegalArgumentException("Customer with identity "+identity+" already exists." );
		customerRepository.save(customer);
	}

}
