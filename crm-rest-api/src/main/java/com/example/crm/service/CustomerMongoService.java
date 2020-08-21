package com.example.crm.service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crm.domain.CustomerDocument;
import com.example.crm.event.CustomerAcquiredEvent;
import com.example.crm.repository.CustomerDocumentRepository;

@Service
public class CustomerMongoService {
	@Autowired
	private CustomerDocumentRepository customerRepository;
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public CustomerDocument findCustomerByIdentity(String identity) {
		Supplier<IllegalArgumentException> illegalArgumentException = () -> new IllegalArgumentException(
				"Cannot find customer with identity " + identity);
		return customerRepository.findById(identity).orElseThrow(illegalArgumentException);
	}

	public List<CustomerDocument> findCustomersWithPagination(int page, int size) {
		return customerRepository.findAll(PageRequest.of(page, size)).getContent();
	}

	public void createCustomer(CustomerDocument customer) {
		var identity = customer.getIdentity();
		if (customerRepository.findById(identity).isPresent())
			throw new IllegalArgumentException("Customer with identity " + identity + " already exists.");
		customerRepository.save(customer);
		var event = new CustomerAcquiredEvent(UUID.randomUUID().toString(), new Date(), customer);
		eventPublisher.publishEvent(event);
	}

	public void updateCustomer(CustomerDocument customer) {
		var identity = customer.getIdentity();
		var optionalCustomer = customerRepository.findById(identity);
		if (optionalCustomer.isEmpty())
			throw new IllegalArgumentException("Customer with identity " + identity + " does not exist.");
		var managedCustomer = optionalCustomer.get();
		managedCustomer.setFullname(customer.getFullname());
		managedCustomer.setPhoto(customer.getPhoto());
		managedCustomer.setAddress(customer.getAddress());
		// customerRepository.save(managedCustomer);
	}

	// Distributed Transaction, XA Transaction
	// Transaction Coordinator -> 2P(hase)C(ommit) -> NOT Scalable!
	// Hard/Soft State, Eventual Consistent -> Saga/Compensation/Event Sourcing/...
	// DDD, Hexagonal Architecture
	@Transactional
	public void patchCustomer(String identity, Map<String, Object> patchValues) {
		var optionalCustomer = customerRepository.findById(identity);
		if (optionalCustomer.isEmpty())
			throw new IllegalArgumentException("Customer with identity " + identity + " does not exist.");
		var managedCustomer = optionalCustomer.get();
		var clazz = CustomerDocument.class;
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
				System.err.println("Exception: " + e.getMessage());
			}
		}
		// customerRepository.save(managedCustomer);
	}

	@Transactional
	public CustomerDocument removeCustomerByIdentity(String identity) {
		Supplier<IllegalArgumentException> illegalArgumentException = () -> new IllegalArgumentException(
				"Cannot find customer with identity " + identity);
		var customer = customerRepository.findById(identity).orElseThrow(illegalArgumentException);
		customerRepository.delete(customer);
		return customer;
	}

}
