package com.mdb.route.processors;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.jayway.jsonpath.JsonPath;
import com.mdb.camel.json.utils.MdbJsonUtils;
import com.mdb.issues.domain.MdbMetric;

public class AggregateProcessor implements Processor {
	
	@Override
    public void process(Exchange exchange) throws Exception {
		
    	String bodyStringJson = exchange.getIn().getBody(String.class);
    	
        //create json array without root element wrapper.
        List<MdbMetric> mdbMetricsList = JsonPath.read(bodyStringJson, "$.mdbProjects");
        String resultMdbIssueMetrics = MdbJsonUtils.toJson(mdbMetricsList);
        
    	exchange.getOut().setBody(resultMdbIssueMetrics);
    }
}
