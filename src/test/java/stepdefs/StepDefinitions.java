package stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLEncoder.*;

public class StepDefinitions {

    private URL url;
    private HttpURLConnection con;
    private int responseCode;
    private StringBuffer response;


    @Given("A connection to \"([^\"]*)\"$")
    public void connection(URL url) throws MalformedURLException {

        url = new URL("http://httpbin.org/ip");


        try {
            con = (HttpURLConnection) url.openConnection(); //-->> conection
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("conecction succesfull");
    }

    @When("User makes a get request")
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


    //@And("^The content is shown on the results:$")
    @And("^the JSON response should have origin with value \"(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)\"$")
    public void body_content(int arg1, int arg2, int arg3, int arg4) throws IOException {
        BufferedReader  in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        //ObjectMapper mapper = new ObjectMapper();

        //String jsonStr = mapper.writeValueAsString(response);
        //result = mapper.readValue(jsonStr,StringBuffer.class);

        System.out.println(response);
        Assert.assertEquals(arg1+"."+arg2+"."+arg3+"."+arg4, response.substring(14,29),"Body content are not matching");

    }



}
