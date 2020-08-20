package com.example.crm.application.business;

import java.util.Optional;
import java.util.UUID;

import com.example.crm.application.CrmApplication;
import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;
import com.example.crm.events.CustomerAcquiredEvent;
import com.example.crm.events.CustomerLosedEvent;
import com.example.crm.infrastructure.EventPushlisher;
import com.example.crm.repository.CustomerRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class SimpleCrmApplication implements CrmApplication {
	private CustomerRepository customerRepository; // SPI #1
	private EventPushlisher eventPushlisher; // SPI #1

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void setEventPushlisher(EventPushlisher eventPushlisher) {
		this.eventPushlisher = eventPushlisher;
	}

	@Override
	public boolean acquireCustomer(Customer customer) {
		customerRepository.save(customer);
		eventPushlisher.publishEvent(new CustomerAcquiredEvent(UUID.randomUUID().toString(), "customers", customer));
		return true;
	}

	@Override
	public Optional<Customer> loseCustomer(TcKimlikNo identity) {
		var customer = customerRepository.findByIdentity(identity);
		if (customer.isEmpty())
			return Optional.empty();
		var cust = customer.get();
		customerRepository.remove(cust);
		eventPushlisher.publishEvent(new CustomerLosedEvent(UUID.randomUUID().toString(), "customers", cust));
		return Optional.of(cust);
	}

}
