Feature: Test rest api
  As a QA of Datio company
  I want to validate endpoints [get and post requests]
  So that will reduce the time that developers invest
  in debugging errors and finally it will reduce costs.
  Scenario: Succesfull status response and body content
    Given A connection to "http://httpbin.org/ip"
    When  User makes a get request
    Then  The status response should be 200
    And the JSON body response should be "Valid"














