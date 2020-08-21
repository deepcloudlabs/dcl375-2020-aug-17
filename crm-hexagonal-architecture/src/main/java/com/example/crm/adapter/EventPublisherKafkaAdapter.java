package com.example.crm.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.events.BusinessEvent;
import com.example.crm.infrastructure.EventPushlisher;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class EventPublisherKafkaAdapter implements EventPushlisher {
	@Autowired
	private KafkaTemplate<String, BusinessEvent> kafkaTemplate;

	@Override
	public void publishEvent(BusinessEvent event) {
		kafkaTemplate.send("customers", event);
	}

}
