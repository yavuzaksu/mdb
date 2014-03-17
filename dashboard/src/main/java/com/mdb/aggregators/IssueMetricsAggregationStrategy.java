package com.mdb.aggregators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.jayway.jsonpath.JsonPath;
import com.mdb.issues.domain.IssueMetric;
import com.mdb.issues.domain.IssueMetricsList;

public class IssueMetricsAggregationStrategy implements AggregationStrategy {
	 
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        Message oldIn = oldExchange.getIn();
        String oldBody = oldIn.getBody(String.class);
        String project1Json = getIsuueMetricsAsMdbJson(oldBody); 
        
        Message newIn = newExchange.getIn();
        String newBody = newIn.getBody(String.class);
        IssueMetric project2Node = getIsuueMetricsAsMdbPojo(newBody); 
        
        String finalIssueMetricsJson = createIssueMetricsArrayJson(project1Json, project2Node);
        
        oldExchange.getIn().setBody(finalIssueMetricsJson);
        return oldExchange;

        // OR
        //newIn.setBody(finalIssueMetricsJson);
        //return newExchange;

    }

	private IssueMetric getIsuueMetricsAsMdbPojo(String newBody) {
		List<Object> issueList = JsonPath.read(newBody, "$.issues");
				
		if(JsonPath.read(newBody, "$.paging") == null 
				|| JsonPath.read(newBody, "$.issues") == null 
				|| issueList.size() == 0){
			
			// No issues present. So don't write to the JSON array.
			return null;
		}
		
		int total = JsonPath.read(newBody, "$.paging.total");
        String projectName = JsonPath.read(newBody, "$.issues[0].severity");
        
        IssueMetric project = new IssueMetric();
		project.setSeverity(projectName);
		project.setTotal(total);
		
		return project;
	}

	private String createIssueMetricsArrayJson(String oldAggregatedJson, IssueMetric newProjectNode) {
		List<IssueMetric> projectList = JsonPath.read(oldAggregatedJson, "$.mdbProjects");
		
		if(newProjectNode != null){
	        projectList.add(newProjectNode);
		}
        
        IssueMetricsList issueListObject = new IssueMetricsList();
        issueListObject.setMdbProjects(projectList);
        
        String resultMdbIssueMetrics = toJson(issueListObject);
		
		return resultMdbIssueMetrics;
	}

	private String getIsuueMetricsAsMdbJson(String issuesJson) {
		// TODO Auto-generated method stub
		if(JsonPath.read(issuesJson, "$.mdbProjects") != null) {
			
			return issuesJson;
		} 

		String resultMdbIssueMetrics = createSingleElementArrayJson(issuesJson);
	
		return resultMdbIssueMetrics;
		
	}

	private String createSingleElementArrayJson(String issuesJson) {

        IssueMetric projectNode = getIsuueMetricsAsMdbPojo(issuesJson);

		List<IssueMetric> projectList = new ArrayList<>();
		
		if(projectNode != null){
	        projectList.add(projectNode);
		}
       	        
        IssueMetricsList issueListObject = new IssueMetricsList();
        issueListObject.setMdbProjects(projectList);
	
        String resultMdbIssueMetrics = toJson(issueListObject);
		return resultMdbIssueMetrics;
	}

	private String toJson(IssueMetricsList issueListObject) {
		ObjectMapper mapper = new ObjectMapper();
        String resultMdbIssueMetrics = "{}";
		try {
			resultMdbIssueMetrics = mapper.writeValueAsString(issueListObject);
			
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMdbIssueMetrics;
	}
 

}