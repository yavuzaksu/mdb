package com.mdb.route.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BasicProcessor implements Processor {
	
	@Override
    public void process(Exchange exchange) throws Exception {
    	exchange.getOut();
    }
}

