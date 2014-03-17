package com.mdb.route.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class AggregateProcessor implements Processor {
	
	@Override
    public void process(Exchange exchange) throws Exception {
		
    	String string = exchange.getIn().getBody(String.class);
    	
    	exchange.getOut().setBody(string);
    }
}

