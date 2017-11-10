package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.AssertTest;

import java.net.URL;
import java.io.IOException;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.*;
import java.net.URLEncoder;



public class StepDefinitions {

    private URL url;
    private HttpURLConnection con;
    private int responseCode;



    @Given("A connection to \"([^\"]*)\"$")
    public void connection(URL url) throws MalformedURLException {

        url = new URL("http://httpbin.org/ip");


        try {
            con = (HttpURLConnection) url.openConnection(); //-->> conection
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("conection succesfull");
    }

    @When("User make a get request")
    public void getRequest() throws IOException {

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        responseCode = con.getResponseCode();
        System.out.println("User did the get request succesfully");

    }

    @Then("The status response should be (\\d+)$")
    public void status(int number){

        System.out.println("Response code: " + responseCode);
        Assert.assertEquals(responseCode,number,"Responses code are not matching");

    }
}
