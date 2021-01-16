package com.citybik.api.client;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CityBikClient {
    private Logger logger = Logger.getLogger(CityBikClient.class);
    private ExtractableResponse<Response> response = null;

    public void setUp() {
        RestAssured.baseURI = "http://api.citybik.es/v2";
    }

    public void executeCityBikApi() {
        response = given().log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/networks")
                .then().extract();
        logger.info("Response -> " + response.asString());
    }

    public void verifyApiReturnsJsonResponse() {
        assertNotNull(response);
        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.response().contentType());
    }

    public void verifyApiReponseContainsCountryAndLatitudeAndLongitude() {
        List<HashMap<String, Object>> networks = response.jsonPath().getList("networks");
        for (HashMap<String, Object> network : networks) {
            if (((String) network.get("id")).contains("frankfurt")) {
                HashMap<String, Object> location = (HashMap<String, Object>) network.get("location");
                logger.info(location);
                assertEquals(location.get("city"), "Frankfurt", "Response should contains City Frankfurt");
                assertEquals(location.get("country"), "DE", "Response should contains Country German");
                assertNotNull(location.get("latitude"));
                assertNotNull(location.get("longitude"));
            }
        }
    }
}