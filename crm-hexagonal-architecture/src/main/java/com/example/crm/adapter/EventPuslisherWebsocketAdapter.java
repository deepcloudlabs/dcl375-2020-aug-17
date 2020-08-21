package com.example.crm.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.example.crm.events.BusinessEvent;
import com.example.crm.infrastructure.EventPushlisher;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
//@Service
public class EventPuslisherWebsocketAdapter implements EventPushlisher {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Override
	public void publishEvent(BusinessEvent event) {
		messagingTemplate.convertAndSend("changes", event);
	}

}
