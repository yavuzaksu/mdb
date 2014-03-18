package com.mdb.aggregators;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.jayway.jsonpath.JsonPath;
import com.mdb.camel.json.utils.MdbJsonUtils;
import com.mdb.issues.domain.IssueMetricsList;
import com.mdb.issues.domain.MdbMetric;

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
        MdbMetric project2Node = getIsuueMetricsAsMdbPojo(newBody); 
        
        String finalIssueMetricsJson = createIssueMetricsArrayJson(project1Json, project2Node);
        
        oldExchange.getIn().setBody(finalIssueMetricsJson);
        return oldExchange;

        // OR
        //newIn.setBody(finalIssueMetricsJson);
        //return newExchange;

    }

	private MdbMetric getIsuueMetricsAsMdbPojo(String newBody) {
		List<Object> issueList = JsonPath.read(newBody, "$.issues");
				
		if(JsonPath.read(newBody, "$.paging") == null 
				|| JsonPath.read(newBody, "$.issues") == null 
				|| issueList.size() == 0){
			
			// No issues present. So don't write to the JSON array.
			return null;
		}
		
		int total = JsonPath.read(newBody, "$.paging.total");
        String projectName = JsonPath.read(newBody, "$.issues[0].severity");
        
        MdbMetric project = new MdbMetric();
		project.setSeverity(projectName);
		project.setTotal(total);
		
		return project;
	}

	private String createIssueMetricsArrayJson(String oldAggregatedJson, MdbMetric newProjectNode) {
		List<MdbMetric> projectList = JsonPath.read(oldAggregatedJson, "$.mdbProjects");
		
		if(newProjectNode != null){
	        projectList.add(newProjectNode);
		}
        
        IssueMetricsList issueListObject = new IssueMetricsList();
        issueListObject.setMdbProjects(projectList);
        
        String resultMdbIssueMetrics = MdbJsonUtils.toJson(issueListObject);
        
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

        MdbMetric projectNode = getIsuueMetricsAsMdbPojo(issuesJson);

		List<MdbMetric> projectList = new ArrayList<>();
		
		if(projectNode != null){
	        projectList.add(projectNode);
		}
       	        
        IssueMetricsList issueListObject = new IssueMetricsList();
        issueListObject.setMdbProjects(projectList);
	
        String resultMdbIssueMetrics = MdbJsonUtils.toJson(issueListObject);
		return resultMdbIssueMetrics;
	}


}