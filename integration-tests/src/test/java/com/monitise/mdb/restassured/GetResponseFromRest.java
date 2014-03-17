package com.monitise.mdb.restassured;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.monitise.mdb.restconfig.ConfigurationManager;


public class GetResponseFromRest {
	public static JsonPath getJsonPathFromRestApi(String limit) {
		JsonPath pathFromRest = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.when()

				.get(ConfigurationManager.getRestUrl()
						+ "/mdb/api/project/worse/" + limit).then()

				.extract().body().jsonPath();

		return pathFromRest;
	}

	public static void getIssues(String severity) {

		Response response = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.when()
				.get(ConfigurationManager.getSonarUrl()
						+ "/mdb/api/project/issues/"
						+ severity.toLowerCase()).then().extract().response();
		response.body().prettyPrint();

	}
}