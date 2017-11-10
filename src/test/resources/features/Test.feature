Feature: Test rest api
  As a QA
  I want to validate endpoints [get and post requests]
  So that will reduce the time that developers invest
  in debugging errors and finally it will reduce costs.

  Scenario: Get
    Given A connection to "https://httpbin.org/ip"
    When  User make a get request
    Then  The status response should be 200
    
