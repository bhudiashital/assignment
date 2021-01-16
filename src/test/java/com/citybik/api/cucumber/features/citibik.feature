Feature: As a biker I would like to know the exact location of city bikes around the world in a given application.

  Scenario: As a user I want to verify that the city Frankfurt is in Germany and return their corresponded latitude and longitude
    Given I have citibik api
    When I access the api
    Then I should see json response
    And I should see Frankfurt is in Germany and latitude and longitude in the response