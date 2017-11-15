# Rest_API_test_cucumber
- This repo is about how to test a  REST API with cucumber.
- Datio Platform Automation API endpoints (GET, POST, PUT, DELETE,ETC) will be tested.

- For now this project tests GET responses of projects in Automation API:
 a 200 response code, validate JSON body response against a schema of a project and also of several projects.

Requirements: mvn(3.3.9) and  Intellij (optional) installed.

 Steps:

 1 Do a fork of the project

 2 Clone the project in your local

 3 If you are using maven, in the root of the project run :

```
mvn verify

```
  3.1 If you are using Intellij, just open the project cloned and then click on the top bar 'Run'. 
