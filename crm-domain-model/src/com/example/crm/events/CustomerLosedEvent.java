package com.example.crm.events;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class CustomerLosedEvent extends BusinessEvent {

	public CustomerLosedEvent() {
	}

	public CustomerLosedEvent(String eventId, String topic, Object data) {
		super(eventId, topic, data);
	}

}
