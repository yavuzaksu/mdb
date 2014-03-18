package com.monitise.mdb.restconfig;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.path.json.JsonPath;

/**
 * @author bhavanimagam Class which prints the coverage percentile for all
 *         projects.
 */
public class GetResponseFromSonar {
	// CHECKSTYLE:OFF
	public static JsonPath getJsonPathFromSonarApi(String limit) {
		/*
		 * ex: http://localhost:9000/api/resources/?metrics=coverage&limit=10
		 */
		JsonPath pathFromSonar = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.when()

				.get(ConfigurationManager.getSonarUrl()
						+ "/api/resources/?metrics=coverage&limit=" + limit)
				.then()

				.extract().body().jsonPath();

		return pathFromSonar;
	}

	public static JsonPath getIssues(String severity) {
		/*
		 * ex: http://localhost:9000/api/issues/search?statuses=OPEN&severities=MAJOR
		 */
		JsonPath path = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.when()
				.get(ConfigurationManager.getSonarUrl()
						+ "/api/issues/search?statuses=OPEN&severities="+severity).then()
				.extract().body().jsonPath();
		
		return path;
	}
	
	

}
