package com.mdb.route.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.mdb.camel.json.utils.MdbJsonUtils;

public class ResourcesJsonTransformer implements Processor{

	@Override
    public void process(Exchange exchange) throws Exception {		
    	String bodyString = exchange.getIn().getBody(String.class);
    	
    	// parse the JSON and get the UI related info.   	
    	String coverageReportJson = createCoverageReportJson(bodyString);    	
    	exchange.getOut().setBody(coverageReportJson);
    }

	private String createCoverageReportJson(String bodyString) {		
		String mdbJsonString = MdbJsonUtils.createMdbResourcesJsonArray(bodyString, "$.[*].name", "$.[*].msr[*].val");		
		return mdbJsonString;
	}

}
