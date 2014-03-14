package com.monitise.mdb.restassured;

import static com.jayway.restassured.RestAssured.given;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.monitise.mdb.restconfig.ConfigurationManager;

/**
 * @author bhavanimagam Calculates average code coverage on all projects using
 *         json path.
 */
public class GetAvgUsingJsonPath {
    // CHECKSTYLE:OFF

    @Test(priority = 0, dataProvider = "limitProvider")
    public double getAvgCoverageFromSonar(String limit) {
        /*
         * Get all the projects coverage metrics and extract jsonpath.
         */
        JsonPath pathFromSonar = getJsonPathFromSonarApi(limit);
        double totalCoverage = 0;

        /*
         * Get list of coverage values and iterate through the values Extract
         * each value and add them to get total coverage Calculate average
         * coverage with total coverage / number of projects
         */
        int numOfProjects = pathFromSonar.getList("msr.val").size();

        Iterator<Object> itr = pathFromSonar.getList("msr.val").iterator();
        System.out.println(pathFromSonar.getList("id"));
        System.out.println(pathFromSonar.getList("name"));
        while (itr.hasNext()) {
            String actString = (itr.next().toString().replace("[", ""));
            actString = (actString.replace("]", ""));
            totalCoverage = totalCoverage + Double.parseDouble(actString);

        }
        double avg = totalCoverage / numOfProjects;
        System.out.println("Average coverage of all projects from sonar" + avg);
        return avg;

    }

    @Test(priority = 1, dataProvider = "limitProvider")
    public double getAvgCoverageFromRest(String limit) {
        /*
         * Get all the projects coverage metrics and extract jsonpath.
         */
        JsonPath pathFromRest = getJsonPathFromRestApi(limit);

        double totalCoverage = 0;

        /*
         * Get list of coverage values and iterate through the values Extract
         * each value and add them to get total coverage Calculate average
         * coverage with total coverage / number of projects
         */
        List<Object> coverageList = pathFromRest.getList("msr.val");
        int numOfProjects = coverageList.size();
        Iterator<Object> itr = pathFromRest.getList("msr.val").iterator();
        System.out.println(pathFromRest.getList("id"));
        System.out.println(pathFromRest.getList("name"));
        while (itr.hasNext()) {
            String actString = (itr.next().toString().replace("[", ""));
            actString = (actString.replace("]", ""));
            totalCoverage = totalCoverage + Double.parseDouble(actString);

        }
        double avg = totalCoverage / numOfProjects;
        System.out.println("Average coverage of all projects from rest" + avg);
        return avg;
    }

    @Test(priority = 2, dataProvider = "limitProvider")
    public void assertTheResults(String limit) {
        Assert.assertEquals(getAvgCoverageFromRest(limit),
                getAvgCoverageFromSonar(limit));
    }

    public JsonPath getJsonPathFromRestApi(String limit) {
        JsonPath pathFromRest = given()
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .when()

                .get(ConfigurationManager.getRestUrl()
                        + "/mdb/api/project/worse/" + limit).then()

                .extract().body().jsonPath();

        return pathFromRest;
    }

    public JsonPath getJsonPathFromSonarApi(String limit) {
        JsonPath pathFromSonar = given()
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .when()

                .get(ConfigurationManager.getSonarUrl()
                        + "/api/resources/?metrics=coverage&limit=" + limit)
                .then()

                .extract().body().jsonPath();

        return pathFromSonar;
    }

    @Test(priority = 3, dataProvider = "limitProvider")
    public void assertListOfIds(String limit) {
        JsonPath pathFromRest = getJsonPathFromRestApi(limit);
        List<Object> projectIdsFromRest = pathFromRest.get("id");
        JsonPath pathFromSonar = getJsonPathFromRestApi(limit);
        List<Object> projectIdsFromSonar = pathFromSonar.get("id");
        Assert.assertEquals(projectIdsFromRest, projectIdsFromSonar);

    }

    @DataProvider(name = "limitProvider")
    public static Object[][] limits() {
        return new Object[][] { { "1" }, { "5" }, { "10" } };
    }
}
