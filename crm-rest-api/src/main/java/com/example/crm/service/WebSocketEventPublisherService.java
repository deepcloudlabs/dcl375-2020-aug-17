package com.example.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.event.CustomerAcquiredEvent;

@Service
public class WebSocketEventPublisherService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
	@EventListener
	public void listenEvent(CustomerAcquiredEvent event) {
		System.err.println("Event received: "+event);
		messagingTemplate.convertAndSend("/topic/changes", event);
	}
}
