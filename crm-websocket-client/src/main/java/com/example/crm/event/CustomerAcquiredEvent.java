package com.example.crm.event;

import java.util.Date;

import com.example.crm.domain.CustomerDocument;

public class CustomerAcquiredEvent {
	private String eventId;
	private Date date;
	private CustomerDocument customer;

	public CustomerAcquiredEvent() {
	}

	public CustomerAcquiredEvent(String eventId, Date date, CustomerDocument customer) {
		this.eventId = eventId;
		this.date = date;
		this.customer = customer;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CustomerDocument getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDocument customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerAcquiredEvent [eventId=" + eventId + ", date=" + date + ", customer=" + customer + "]";
	}

}
