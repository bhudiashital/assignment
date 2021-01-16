package com.citybik.api.cucumber.stepsDef;

import com.citybik.api.client.CityBikClient;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CitybikStepDef {
    private CityBikClient cityBikClient;
    @Given("I have citibik api")
    public void init() {
        cityBikClient = new CityBikClient();
        cityBikClient.setUp();
    }

    @When("I access the api")
    public void init1() {
        cityBikClient.executeCityBikApi();
    }

    @Then("I should see json response")
    public void init2() {
        cityBikClient.verifyApiReturnsJsonResponse();
    }

    @And("I should see Frankfurt is in Germany and latitude and longitude in the response")
    public void init3() {
        cityBikClient.verifyApiReponseContainsCountryAndLatitudeAndLongitude();
    }
}
