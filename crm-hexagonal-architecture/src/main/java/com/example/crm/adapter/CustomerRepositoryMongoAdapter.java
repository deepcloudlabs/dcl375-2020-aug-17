package com.example.crm.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.crm.document.CustomerDocument;
import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;
import com.example.crm.repository.CustomerMongoRepository;
import com.example.crm.repository.CustomerRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Repository
public class CustomerRepositoryMongoAdapter implements CustomerRepository {
	@Autowired
	private CustomerMongoRepository empRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public Optional<Customer> findByIdentity(TcKimlikNo identity) {
		Optional<CustomerDocument> empDoc = empRepo.findById(identity.getValue());
		if (!empDoc.isEmpty())
			return Optional.empty();
		var customer = mapper.map(empDoc.get(), Customer.class);
		return Optional.of(customer);
	}

	@Override
	public void save(Customer customer) {
		var employeeDocument = new CustomerDocument();
		employeeDocument.setIdentity(customer.getIdentity().getValue());
		var fullname = customer.getFullname();
		employeeDocument.setFullname(fullname.getFirst() + " " + fullname.getLast());
		employeeDocument.setEmail(customer.getEmail().toString());
		employeeDocument.setPhone(customer.getPhone().toString());
		employeeDocument.setPhoto(new String(customer.getPhoto().getValue()));
		employeeDocument.setBirthYear(customer.getBirthYear().getValue());
		employeeDocument.setAddress(customer.getAddress().toString());
		empRepo.save(employeeDocument);
	}

	@Override
	public void remove(Customer customer) {
		empRepo.deleteById(customer.getIdentity().getValue());
	}

}
