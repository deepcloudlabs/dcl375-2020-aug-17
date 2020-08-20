package com.example.crm.application;

import java.util.Optional;

import com.example.crm.domain.Customer;
import com.example.crm.domain.TcKimlikNo;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface CrmApplication {

	boolean acquireCustomer(Customer customer);

	Optional<Customer> loseCustomer(TcKimlikNo identity);

}