package com.monitise.mdb.restassured;

import static com.jayway.restassured.RestAssured.given;

import java.util.Iterator;

import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.monitise.mdb.restconfig.ConfigurationManager;

/**
 * @author bhavanimagam Calculates average code coverage on all projects using
 *         json object and json array.
 */

public class GetAvgUsingJsonObject {
	// CHECKSTYLE:OFF
	@Test
	public void getProjects() {
		/*
		 * Get response of metrics for all projects as a string
		 */
		String string = given()
				.contentType("JSON")
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.when()

				.get(ConfigurationManager.getSonarUrl()
						+ "/api/resources/?metrics=coverage").then()

				.extract().body().asString();
		int numOfProjects = 0;
		JsonParser parser = new JsonParser();
		/*
		 * Parse the string response into json array Iterate through json array
		 * to access internal values Extract the jsonarray from json object for
		 * coverage % which is inside "msr" Iterate through jsonarray and get
		 * internal values for coverage as double Calculate total coverage for
		 * all projects Calculate avg coverage using totalcoverage/number of
		 * projects
		 */
		JsonArray jArray = parser.parse(string).getAsJsonArray();
		Iterator<JsonElement> itr = jArray.iterator();
		double totalCoverage = 0;
		while (itr.hasNext()) {
			JsonObject jsonObj = itr.next().getAsJsonObject();
			System.out.println("name of the project :" + "\n"
					+ jsonObj.get("name"));
			JsonArray jsonArray = (JsonArray) jsonObj.get("msr");
			Iterator<JsonElement> itr1 = jsonArray.iterator();
			while (itr1.hasNext()) {
				JsonObject obj = itr1.next().getAsJsonObject();
				System.out.println("covergare for above project:" + "\n"
						+ obj.get("val"));
				Double element = obj.get("val").getAsDouble();
				totalCoverage = totalCoverage + element;
				numOfProjects = numOfProjects + 1;
			}
		}
		System.out.println("average coverage of all projects :" + "\n"
				+ totalCoverage / numOfProjects);
	}
}
