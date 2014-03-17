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
						+ "/mdb/project/coverage/worse" + limit).then()

				.extract().body().jsonPath();

		return pathFromRest;
	}

	public static JsonPath getIssues() {

		JsonPath issues = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.contentType("JSON")
				.when()
				.get(ConfigurationManager.getRestUrl()
						+ "/mdb/project/sonar/issues/aggregated").then().extract()
				.body().jsonPath();
		return issues;
	}
}