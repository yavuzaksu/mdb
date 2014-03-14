package com.monitise.mdb.restassured;

import static com.jayway.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.monitise.mdb.restconfig.ConfigurationManager;

public class GetUsers {
    // CHECKSTYLE:OFF

    @Test
    public void getUsers() {
        Response response = given()
                .header("Authorization", "Basic YWRtaW46YWRtaW4=").when()
                .get(ConfigurationManager.getSonarUrl() + "/api/users/search")
                .then().extract().response();
        System.out.println(response.body().prettyPrint());

    }

}
