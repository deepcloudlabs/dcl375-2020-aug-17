package com.example.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crm.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{
	// 1. JPQL (JPa Query Language) -> table->entity class, column->field
	//@Query("select c from Customer c where c.birthYear between :from and :to")
	// 2. Native Query -> SQL -> 
	//@Query(value="select c from customers c where c.birth_year between ? and ?",nativeQuery = true )
    public List<Customer> findAllByBirthYearBetween(int from,int to);
}
