package com.example.crm.events;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class CustomerAcquiredEvent extends BusinessEvent {

	public CustomerAcquiredEvent() {
	}

	public CustomerAcquiredEvent(String eventId, String topic, Object data) {
		super(eventId, topic, data);
	}

}
