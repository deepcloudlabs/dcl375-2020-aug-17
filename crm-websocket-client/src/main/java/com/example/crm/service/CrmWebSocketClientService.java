package com.example.crm.service;

import java.lang.reflect.Type;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.example.crm.event.CustomerAcquiredEvent;

@Service
public class CrmWebSocketClientService implements StompSessionHandler {
    @Autowired
    private WebSocketStompClient stompClient;
    
	@PostConstruct
	public void connectToWebSocketServer() {
		stompClient.connect("ws://localhost:4001/crm/api/v1/changes", this);
	}
	
	@Override
	public Type getPayloadType(StompHeaders headers) {
		return CustomerAcquiredEvent.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		var event = (CustomerAcquiredEvent) payload;
		System.err.println(event);
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		System.err.println("Connected to the websocket server.");
		session.subscribe("/topic/changes", this);
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		System.err.println("handleException: "+ exception.getMessage());
		exception.printStackTrace(System.err);
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		System.err.println("handleTransportError: "+ exception.getMessage());
		exception.printStackTrace(System.err);
	}

}
