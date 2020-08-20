package com.example.crm.events;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class BusinessEvent {
	private String eventId;
	private String topic;
	private Object data;

	public BusinessEvent() {
	}

	public BusinessEvent(String eventId, String topic, Object data) {
		this.eventId = eventId;
		this.topic = topic;
		this.data = data;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BusinessEvent [eventId=" + eventId + ", topic=" + topic + ", data=" + data + "]";
	}

}
