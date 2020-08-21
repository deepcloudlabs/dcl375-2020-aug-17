package com.example.crm.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.crm.document.CustomerDocument;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface CustomerMongoRepository extends MongoRepository<CustomerDocument, String> {
	List<CustomerDocument> findAllByBirthYearBetween(int fromYear, int toYear);

	@Query(value = "{'birthYear': {'$gt': ?0, '$lt': ?1}}")
	List<CustomerDocument> araBul(int fromYear, int toYear);

}
