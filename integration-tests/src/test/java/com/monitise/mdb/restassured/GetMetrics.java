package com.monitise.mdb.restassured;

import static com.jayway.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;
import com.jayway.restassured.response.Response;
import com.monitise.mdb.restconfig.ConfigurationManager;

public class GetMetrics {
	//CHECKSTYLE:OFF
	@Test
	public void getProjects() {
		JsonPath.config = new JsonPathConfig();
		Response response = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=").when()
				.get(ConfigurationManager.getSonarUrl() + "/api/metrics").then()
				.extract().response();
		response.body().prettyPrint();

	}
}
