package com.monitise.mdb.restconfig;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.path.json.JsonPath;

public class GetResponseFromRest {
	public static JsonPath getJsonPathFromRestApiWithLimit(String limit) {
		JsonPath pathFromRest = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.when()

				.get(ConfigurationManager.getRestUrl()
						+ "/mdb/rs/project/coverage/worse" + limit).then()

				.extract().body().jsonPath();
		return pathFromRest;
	}
	public static JsonPath getJsonPathFromRestApi() {
		/*
		 * http://localhost:8085/mdb/project/coverage/worse
		 */
		JsonPath pathFromRest = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.when()

				.get(ConfigurationManager.getRestUrl()
						+ "/mdb/rs/project/coverage/worse").then()

				.extract().body().jsonPath();
System.out.println(pathFromRest.prettyPrint());
		return pathFromRest;
	}

	public static JsonPath getIssues() {
/*
 * http://localhost:8085/mdb/project/sonar/issues/aggregated.
 */
		JsonPath issues = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.contentType("JSON")
				.when()
				.get(ConfigurationManager.getRestUrl()
						+ "/mdb/rs/project/sonar/issues/aggregated").then().extract()
				.body().jsonPath();
		return issues;
	}
}