package com.monitise.mdb.restassured;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.monitise.mdb.restconfig.ConfigurationManager;

/**
 * @author bhavanimagam Class which prints the coverage percentile for all
 *         projects.
 */
public class GetResponseFromSonar {
	// CHECKSTYLE:OFF
	public static JsonPath getJsonPathFromSonarApi(String limit) {
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

		JsonPath path = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.when()
				.get(ConfigurationManager.getSonarUrl()
						+ "/api/issues/search?statuses=OPEN&severities="+severity).then()
				.extract().body().jsonPath();
		
		return path;
	}
	
	

}
