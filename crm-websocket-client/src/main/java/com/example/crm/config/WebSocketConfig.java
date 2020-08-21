package com.example.crm.config;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.ContainerProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@Configuration
public class WebSocketConfig {

	@Bean
	public WebSocketClient webSocketClient() {
		var container = ContainerProvider.getWebSocketContainer();
		container.setDefaultMaxTextMessageBufferSize(16 * 1024 * 1024); // 16M
		return new StandardWebSocketClient(container);
	}

	@Bean
	public WebSocketStompClient webSocketStompClient(WebSocketClient client) {
		List<Transport> transports = new ArrayList<>(1);
		transports.add(new WebSocketTransport(client));
		var transport = new SockJsClient(transports);
		final var webSocketStompClient = new WebSocketStompClient(transport);
		webSocketStompClient.setInboundMessageSizeLimit(Integer.MAX_VALUE);
		webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
		return webSocketStompClient;
	}

}
