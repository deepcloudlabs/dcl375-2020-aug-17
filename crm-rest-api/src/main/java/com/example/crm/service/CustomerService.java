package com.example.crm.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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

    @Transactional(isolation = Isolation.READ_COMMITTED,  propagation = Propagation.REQUIRES_NEW)
	public void updateCustomer(Customer customer) {
		var identity = customer.getIdentity();
		var optionalCustomer = customerRepository.findById(identity);
		if( optionalCustomer.isEmpty())
			throw new IllegalArgumentException("Customer with identity "+identity+" does not exist." );
		var managedCustomer = optionalCustomer.get();
		managedCustomer.setFullname(customer.getFullname());
		managedCustomer.setPhoto(customer.getPhoto());
		managedCustomer.setAddress(customer.getAddress());
		// customerRepository.save(managedCustomer);
	}

	@Transactional
	public void patchCustomer(String identity, Map<String, Object> patchValues) {
		var optionalCustomer = customerRepository.findById(identity);
		if( optionalCustomer.isEmpty())
			throw new IllegalArgumentException("Customer with identity "+identity+" does not exist." );
		var managedCustomer = optionalCustomer.get();
		var clazz = Customer.class;
		for (var entry : patchValues.entrySet()) {
			// Use reflection to update managedCustomer
			Field declaredField;
			try {
				declaredField = clazz.getDeclaredField(entry.getKey());
				if (Objects.nonNull(declaredField)) {
					declaredField.setAccessible(true);
					declaredField.set(managedCustomer, entry.getValue());
					declaredField.setAccessible(false);
				}
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				System.err.println("Exception: "+e.getMessage());
			}
		}
		// customerRepository.save(managedCustomer);
	}

	@Transactional
	public Customer removeCustomerByIdentity(String identity) {
		Supplier<IllegalArgumentException> illegalArgumentException = () -> new IllegalArgumentException("Cannot find customer with identity "+identity);
		var customer = customerRepository.findById(identity)
				                 .orElseThrow(illegalArgumentException);
		customerRepository.delete(customer);
		return customer;
	}

}
