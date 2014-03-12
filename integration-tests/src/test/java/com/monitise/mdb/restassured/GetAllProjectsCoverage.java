package com.monitise.mdb.restassured;

import static com.jayway.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.monitise.mdb.restconfig.ConfigurationManager;

/**
 * @author bhavanimagam Class which prints the coverage percentile for all
 *         projects.
 */
public class GetAllProjectsCoverage {
	// CHECKSTYLE:OFF
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
