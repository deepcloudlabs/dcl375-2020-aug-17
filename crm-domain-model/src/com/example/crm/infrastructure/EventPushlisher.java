package com.example.crm.infrastructure;

import com.example.crm.events.BusinessEvent;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface EventPushlisher {
	public void publishEvent(BusinessEvent event);
}
