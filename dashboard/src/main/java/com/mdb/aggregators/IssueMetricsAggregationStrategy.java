package com.mdb.aggregators;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class IssueMetricsAggregationStrategy implements AggregationStrategy {
	 
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }
 
       
        Message newIn = newExchange.getIn();
        String oldBody = oldExchange.getIn().getBody(String.class);
        String newBody = newIn.getBody(String.class);
        newIn.setBody(oldBody + newBody);
        
        return newExchange;
    }
 
}