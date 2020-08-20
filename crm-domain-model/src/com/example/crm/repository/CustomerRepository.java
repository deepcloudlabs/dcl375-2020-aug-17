package com.example.crm.repository;

import java.util.Optional;

import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface CustomerRepository {
	Optional<Customer> findByIdentity(TcKimlikNo identity);

	void save(Customer employee);

	void remove(Customer employee);
}
