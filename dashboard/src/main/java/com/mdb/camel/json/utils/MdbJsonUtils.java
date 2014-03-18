package com.mdb.camel.json.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.jayway.jsonpath.JsonPath;
import com.mdb.issues.domain.MdbMetric;

public class MdbJsonUtils {

	public static String toJson(Object issueListObject) {
		ObjectMapper mapper = new ObjectMapper();
        String resultMdbIssueMetrics = "[]";
        String indentedJson = "[ \n ]";
		try {
			resultMdbIssueMetrics = mapper.writeValueAsString(issueListObject);
			indentedJson = doPrettyPrint(mapper, resultMdbIssueMetrics);
		} 
		// TODO: These exceptions to be throws as customer MDBException
		catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// return resultMdbIssueMetrics;
		return indentedJson;
	}

	private static String doPrettyPrint(ObjectMapper mapper,
			String resultMdbIssueMetrics) throws IOException,
			JsonParseException, JsonMappingException, JsonGenerationException {
		String indentedJson;
		Object json = mapper.readValue(resultMdbIssueMetrics, Object.class);	
		indentedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		return indentedJson;
	}
	
	public static String createMdbResourcesJsonArray(String jsonString, String... jsonPaths ) {
		
		//TODO: 
		// This is to be a generic method accepting variable number of params, may return
		/*
		array, string, int etc
		 	
		for (int i = 0; i < jsonPaths.length; ++i) {
			
		}
		*/
		List<Double> coverageList = JsonPath.read(jsonString, jsonPaths[1]);
		List<String>  projectNameList = JsonPath.read(jsonString, jsonPaths[0]);
		
		List<MdbMetric> metricNodeList = new ArrayList<MdbMetric>();
		for(int i = 0; i < coverageList.size(); i++){
			
			MdbMetric metricNode = new MdbMetric();
			metricNode.setCodeCoverage(coverageList.get(i));
			metricNode.setProjectName(projectNameList.get(i));
			
			metricNodeList.add(metricNode);
		}
		
		String mdbArrayJson = MdbJsonUtils.toJson(metricNodeList);
		
		return mdbArrayJson;
	}
	
}
