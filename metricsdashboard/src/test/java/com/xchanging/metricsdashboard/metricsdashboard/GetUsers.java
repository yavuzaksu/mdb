package com.xchanging.metricsdashboard.metricsdashboard;

import static com.jayway.restassured.RestAssured.*;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

public class GetUsers {

	@Test
	public void getUsers() {
		Response response = given()
				.header("Authorization", "Basic YWRtaW46YWRtaW4=").when()
				.get(DEFAULT_URI + ":9000" + "/api/users/search").then()
				.extract().response();
		System.out.println(response.body().prettyPrint());

	}

}
