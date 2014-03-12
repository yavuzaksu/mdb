package com.monitise.mdb.restassured;

import static com.jayway.restassured.RestAssured.given;

import java.util.Iterator;

import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.monitise.mdb.restconfig.ConfigurationManager;

/**
 * @author bhavanimagam Calculates average code coverage on all projects using
 *         json path.
 */
public class GetAvgUsingJsonPath {
	// CHECKSTYLE:OFF

	@Test
	public void getAvgCoverage()  {
		/*
		 * Get all the projects coverage metrics and extract jsonpath.
		 */
		JsonPath path = given()
				.contentType("JSON")
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.when()

				.get(ConfigurationManager.getUrl()
						+ "/api/resources/?metrics=coverage").then()

				.extract().body().jsonPath();
		double totalCoverage = 0;
		int numOfProjects = 0;
		/*
		 * Get list of coverage values and iterate through the values Extract each
		 * value and add them to get total coverage Calculate average coverage
		 * with total coverage / number of projects
		 */
		System.out.println(path.getList("msr.val"));
		Iterator<Object> itr = path.getList("msr.val").iterator();
		System.out.println(path.getList("id"));
		System.out.println(path.getList("name"));
		while (itr.hasNext()) {
			String actString = (itr.next().toString().replace("[", ""));
			actString = (actString.replace("]", ""));
			totalCoverage = totalCoverage + Double.parseDouble(actString);
			numOfProjects = numOfProjects + 1;
		}
		double avg = totalCoverage / numOfProjects;
		System.out.println("Average coverage of all projects" + avg);
	}
}
