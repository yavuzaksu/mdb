package com.monitise.mdb.restassured;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.path.json.JsonPath;
import com.monitise.mdb.jsonobjects.Issue;
import com.monitise.mdb.jsonobjects.Severity;
import com.monitise.mdb.restconfig.GetResponseFromRest;
import com.monitise.mdb.restconfig.GetResponseFromSonar;

/*
 *@author bhavanimagam Calculates average code coverage on all projects using json path.
 */
public class CompareResultsFromApis {
	// CHECKSTYLE:OFF

	
	
	  @Test(priority = 0, dataProvider = "limitProvider") public double
	  getAvgCoverageFromSonar(String limit) {
	  
	 /* 
	  * Get all the projects coverage metrics and extract jsonpath.
	  */
	  
	  JsonPath pathFromSonar = GetResponseFromSonar
	  .getJsonPathFromSonarApi(limit); 
	  double totalCoverage = 0;
	  
	  
	 /*
	  * Get list of coverage values and iterate through the values Extract each
	  * value and add them to get total coverage Calculate average coverage with
	  * total coverage / number of projects
	  */
	  int numOfProjects = pathFromSonar.getList("msr.val").size();
	  
	  Iterator<Object> itr = pathFromSonar.getList("msr.val").iterator();
	  System.out.println(pathFromSonar.getList("id"));
	  System.out.println(pathFromSonar.getList("name")); while (itr.hasNext())
	  { String actString = (itr.next().toString().replace("[", "")); actString
	  = (actString.replace("]", "")); totalCoverage = totalCoverage +
	  Double.parseDouble(actString);
	  
	  } 
	  double avg = totalCoverage / numOfProjects;
	  System.out.println("Average coverage of all projects from sonar" + avg);
	  return avg;
	  
	  }
	  
	  @Test(priority = 1) 
	  public double getAvgCoverageFromRest() {
	  
	 /* Get all the projects coverage metrics and extract jsonpath.
	  * 
	  */
	 
	  
	  JsonPath pathFromRest = GetResponseFromRest.getJsonPathFromRestApi();
	  
	  double totalCoverage = 0;
	  
	  
	 /* Get list of coverage values and iterate through the values Extract each
	  *value and add them to get total coverage Calculate average coverage with
	  *total coverage / number of projects
	  */
	  List<Object> coverageList = pathFromRest.getList("msr.val"); 
	  int numOfProjects = coverageList.size(); 
	  Iterator<Object> itr =pathFromRest.getList("msr.val").iterator();
	  System.out.println(pathFromRest.getList("id"));
	  System.out.println(pathFromRest.getList("name")); while (itr.hasNext()) {
	  String actString = (itr.next().toString().replace("[", "")); actString =
	  (actString.replace("]", "")); 
	  totalCoverage = totalCoverage +Double.parseDouble(actString);
	  
	  } 
	  double avg = totalCoverage / numOfProjects;
	  System.out.println("Average coverage of all projects from rest" + avg);
	  return avg; 
	  }
	  
	  @Test(priority = 2, dataProvider = "limitProvider") 
	  public void assertTheResults(String limit) 
	  {
	  Assert.assertEquals(getAvgCoverageFromRest(),getAvgCoverageFromSonar(limit)); 
	  }
	  
	  @Test(priority = 3, dataProvider = "limitProvider") 
	  public void assertListOfIds(String limit) 
	  { 
		  JsonPath pathFromRest = GetResponseFromRest .getJsonPathFromRestApi(); 
		  List<Object> projectIdsFromRest = pathFromRest.get("id"); 
		  JsonPath pathFromSonar = GetResponseFromSonar .getJsonPathFromSonarApi(limit); 
		  List<Object> projectIdsFromSonar = pathFromSonar.get("id");
	  Assert.assertEquals(projectIdsFromRest, projectIdsFromSonar);
	  
	  }
	 
	@Test(priority = 1)
	public void aggregateNumberOfIssues() {
		List<Issue> expectedIssues = new ArrayList<Issue>();
		for (Severity severity : Severity.values()) {
			JsonPath issuesFromSonar = GetResponseFromSonar.getIssues(severity
					.toString());
			int numberOfIssuesFromSonar = issuesFromSonar.get("paging.total");
			expectedIssues.add(new Issue(numberOfIssuesFromSonar, severity));
		}
		JsonPath issuesFromRest = GetResponseFromRest.getIssues();
		Type actualIssuesType = new TypeToken<List<Issue>>() {
		}.getType();
		Gson gson = new Gson();
		List<Issue> actualIssues = gson.fromJson(
				issuesFromRest.get("mdbProjects").toString(), actualIssuesType);
		Assert.assertTrue(actualIssues.size() == expectedIssues.size(),
				"Issues type count not matching ");
		for (Issue expectedIssue : expectedIssues) {
			Assert.assertTrue(actualIssues.contains(expectedIssue),
					"expected issue " + expectedIssue + " not found in "
							+ actualIssues);
		}
	}
	

	@DataProvider(name = "limitProvider")
	public static Object[][] limits() {
		return new Object[][] { { "10" } };
	}

}
