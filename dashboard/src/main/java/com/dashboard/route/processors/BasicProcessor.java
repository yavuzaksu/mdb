package com.dashboard.route.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BasicProcessor implements Processor {
	
	// Should outptut JSON
	@Override
    public void process(Exchange exchange) throws Exception {
		
    	exchange.getOut();   	
    }
}

