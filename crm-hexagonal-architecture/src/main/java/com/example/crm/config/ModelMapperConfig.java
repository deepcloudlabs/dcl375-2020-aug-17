package com.example.crm.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.crm.document.CustomerDocument;
import com.example.crm.domain.Customer;
import com.example.crm.domain.FullName;
import com.example.crm.dto.CustomerRequest;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Configuration
public class ModelMapperConfig {
	private static final Converter<CustomerDocument, Customer> customerDocumentToCustomerConverter = context -> {
		var customerDocument = context.getSource();
		String[] tokens = customerDocument.getFullname().split("\\w+");
		return new Customer.Builder(customerDocument.getIdentity()).fullname(tokens[0], tokens[1])
				.phone(customerDocument.getPhone()).email(customerDocument.getEmail())
				.birthYear(customerDocument.getBirthYear()).address(customerDocument.getAddress())
				.photo(customerDocument.getPhoto().getBytes()).build();
	};
	private static final Converter<Customer, CustomerDocument> customerToCustomerDocumentConverter = (context -> {
		var customer = context.getSource();
		var customerDocument = new CustomerDocument();
		customerDocument.setIdentity(customer.getIdentity().getValue());
		FullName fullname = customer.getFullname();
		customerDocument.setFullname(fullname.getFirst() + " " + fullname.getLast());
		customerDocument.setPhone(customer.getPhone().toString());
		customerDocument.setAddress(customer.getAddress().toString());
		customerDocument.setPhoto(new String(customer.getPhoto().getValue()));
		customerDocument.setBirthYear(customer.getBirthYear().getValue());
		customerDocument.setEmail(customer.getEmail().getValue());
		return customerDocument;
	});
	private static final Converter<CustomerRequest, Customer> customerRequestToCustomerConverter = (context -> {
		var customerRequest = context.getSource();
		var tokens = customerRequest.getFullname().split("\\s+");
		return new Customer.Builder(customerRequest.getIdentity())
				.fullname(tokens[0], tokens[1])
				.email(customerRequest.getEmail())
				.address(customerRequest.getAddress())
				.birthYear(customerRequest.getBirthYear())
				.phone(customerRequest.getPhone())
				.photo(customerRequest.getPhoto().getBytes()).build();
	});

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addConverter(customerRequestToCustomerConverter, CustomerRequest.class, Customer.class);
		mapper.addConverter(customerToCustomerDocumentConverter, Customer.class, CustomerDocument.class);
		mapper.addConverter(customerDocumentToCustomerConverter, CustomerDocument.class, Customer.class);
		return mapper;
	}
}
