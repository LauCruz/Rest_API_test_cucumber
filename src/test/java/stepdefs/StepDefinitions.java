package stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class StepDefinitions {

    private URL url;
    private HttpURLConnection con;
    private int responseCode;
    private StringBuffer response;
    private String valid;
    private String notValid;



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
    @And("^the JSON body response should be \"([^\"]*)\"$")
    public void body_content(String message) throws IOException,ProcessingException {

        File schemaFile = new File("schemaProjects.json");
        File jsonFile = new File("dataProjects.json");


        if (ValidationUtils.isJsonValid(schemaFile, jsonFile)){
            this.valid = "Valid!";
            System.out.println(valid);
        }else{
            this.notValid = "NOT valid!";
            System.out.println(notValid);
        }

        //This body response is saved in a variable call response
        //instead of a file.
        /*ObjectMapper mapper = new ObjectMapper();

        BufferedReader  in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }


        //String jsonStr = mapper.writeValueAsString(response);

        //read JSON like DOM parser
        JsonNode rootNode = mapper.readTree(response.toString());
        JsonNode originNode = rootNode.path("origin");
        System.out.println("origin = " + originNode);*/


        Assert.assertEquals(message,valid,notValid);


    }



}
