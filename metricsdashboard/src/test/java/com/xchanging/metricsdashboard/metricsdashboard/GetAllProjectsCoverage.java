package com.xchanging.metricsdashboard.metricsdashboard;

import static com.jayway.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.xchanging.metricsdashboard.baseproject.ConfigurationManager;

public class GetAllProjectsCoverage {
	@Test
	public void getProjects() {

		Response response = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.when()
				.get(ConfigurationManager.getUrl()
						+ "/api/resources/?metrics=ncloc,coverage").then()
				.extract().response();
		response.body().prettyPrint();

	}
}
