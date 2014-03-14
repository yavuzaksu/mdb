package com.monitise.mdb.restassured;

import static com.jayway.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.monitise.mdb.restconfig.ConfigurationManager;

public class GetCoverageFromRest {
    @Test(dataProvider= "limitProvider")
    public void getAvgCoverageFromRest(String limit)  {
        /*
         * Get all the projects coverage metrics and extract jsonpath.
         */
        System.out.println(ConfigurationManager.getRestUrl()
                + "/mdb/api/project/worse/"+"+limit+");
 JsonPath path =  given()
               .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .when()

                .get(ConfigurationManager.getRestUrl()
                        + "/mdb/api/project/worse/"+limit).then()

                .extract().body().jsonPath();
  System.out.println(path.prettyPrint());
    System.out.println(path.getList("msr.val"));
}
    @DataProvider(name = "limitProvider")
    public static Object[][] limits() {
       return new Object[][] { 
                { "1" }, 
                { "5" },
                { "10" } 
            };
    }
}