package com.monitise.mdb.restassured;

import static com.jayway.restassured.RestAssured.given;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.monitise.mdb.restconfig.ConfigurationManager;

/**
 * @author bhavanimagam Calculates average code coverage on all projects using
 *         json path.
 */
public class CompareResultsFromApis {
	// CHECKSTYLE:OFF
	
static int i =0;
/*	@Test(priority = 0, dataProvider = "limitProvider")
	public double getAvgCoverageFromSonar(String limit) {
		
		 * Get all the projects coverage metrics and extract jsonpath.
		 
		JsonPath pathFromSonar = GetResponseFromSonar
				.getJsonPathFromSonarApi(limit);
		double totalCoverage = 0;

		
		 * Get list of coverage values and iterate through the values Extract
		 * each value and add them to get total coverage Calculate average
		 * coverage with total coverage / number of projects
		 
		int numOfProjects = pathFromSonar.getList("msr.val").size();

		Iterator<Object> itr = pathFromSonar.getList("msr.val").iterator();
		System.out.println(pathFromSonar.getList("id"));
		System.out.println(pathFromSonar.getList("name"));
		while (itr.hasNext()) {
			String actString = (itr.next().toString().replace("[", ""));
			actString = (actString.replace("]", ""));
			totalCoverage = totalCoverage + Double.parseDouble(actString);

		}
		double avg = totalCoverage / numOfProjects;
		System.out.println("Average coverage of all projects from sonar" + avg);
		return avg;

	}

	@Test(priority = 1, dataProvider = "limitProvider")
	public double getAvgCoverageFromRest(String limit) {
		
		 * Get all the projects coverage metrics and extract jsonpath.
		 
		JsonPath pathFromRest = GetResponseFromRest
				.getJsonPathFromRestApi(limit);

		double totalCoverage = 0;

		
		 * Get list of coverage values and iterate through the values Extract
		 * each value and add them to get total coverage Calculate average
		 * coverage with total coverage / number of projects
		 
		List<Object> coverageList = pathFromRest.getList("msr.val");
		int numOfProjects = coverageList.size();
		Iterator<Object> itr = pathFromRest.getList("msr.val").iterator();
		System.out.println(pathFromRest.getList("id"));
		System.out.println(pathFromRest.getList("name"));
		while (itr.hasNext()) {
			String actString = (itr.next().toString().replace("[", ""));
			actString = (actString.replace("]", ""));
			totalCoverage = totalCoverage + Double.parseDouble(actString);

		}
		double avg = totalCoverage / numOfProjects;
		System.out.println("Average coverage of all projects from rest" + avg);
		return avg;
	}

	@Test(priority = 2, dataProvider = "limitProvider")
	public void assertTheResults(String limit) {
		Assert.assertEquals(getAvgCoverageFromRest(limit),
				getAvgCoverageFromSonar(limit));
	}

	@Test(priority = 3, dataProvider = "limitProvider")
	public void assertListOfIds(String limit) {
		JsonPath pathFromRest = GetResponseFromRest
				.getJsonPathFromRestApi(limit);
		List<Object> projectIdsFromRest = pathFromRest.get("id");
		JsonPath pathFromSonar = GetResponseFromSonar
				.getJsonPathFromSonarApi(limit);
		List<Object> projectIdsFromSonar = pathFromSonar.get("id");
		Assert.assertEquals(projectIdsFromRest, projectIdsFromSonar);

	}*/
List<Object> listOfIssuesFromSonar = new LinkedList<>();
@Test(priority = 2, dataProvider = "severityProvider")
public void aggregateNumberOfIssues(String severity) {

	JsonPath issuesFromSonar = GetResponseFromSonar.getIssues(severity);
	int numberOfIssuesFromSonar = issuesFromSonar.get("paging.total");
	listOfIssuesFromSonar.add(numberOfIssuesFromSonar);
}
@Test(priority = 3)
public void assertIssuesList(){
	List<Object> issuesFromRest = GetResponseFromRest.getIssues().get("mdbProjects.total");
	System.out.println(listOfIssuesFromSonar);
	System.out.println(issuesFromRest);
	Assert.assertTrue(issuesFromRest.equals(listOfIssuesFromSonar));
	
}

	@DataProvider(name = "limitProvider")
	public static Object[][] limits() {
		return new Object[][] { { "1" }, { "5" }, { "10" } };
	}

	@DataProvider(name = "severityProvider")
	public static Object[][] severity() {
		return new Object[][] { { "MAJOR" }, { "MINOR" }, { "BLOCKER" } ,{ "CRITICAL" }};
	}
	
	
}
