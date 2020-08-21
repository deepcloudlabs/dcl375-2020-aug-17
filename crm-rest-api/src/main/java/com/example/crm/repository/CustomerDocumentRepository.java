package com.example.crm.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.crm.domain.CustomerDocument;

public interface CustomerDocumentRepository extends MongoRepository<CustomerDocument, String>{

	List<CustomerDocument> findAllByBirthYearBetween(int from, int year);
	
	@Query("{'birthYear': {'$gt': ?0, '$lt': ?1}}") // native query
	List<CustomerDocument> bul(int from, int to);
	
}
