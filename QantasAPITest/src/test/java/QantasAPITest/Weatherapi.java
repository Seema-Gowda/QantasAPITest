package QantasAPITest;

import com.jayway.restassured.response.Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import org.apache.http.util.Asserts;
//import org.junit.Assert;
import Utility.InvocationUtil;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


public class Weatherapi {
    private static Logger logger = Logger.getLogger("Weatherapi.class");
    List<HashMap<String, String>> responseList;
    Response resp;
    private String key;
    private String postCode;
    private String latitude;
    private String longitude;
    private InvocationUtil invokeUtilObj = new InvocationUtil();

    @Given("^I have the api key (.+?)$")
    public void iHaveApiKey(String apiKey) {
        key = apiKey;
    }

    @And("^I have Latitude as (.+?) and longitude as (.+?)$")
    public void iProvideLatitudeAndLongitude(String lat, String longi) {
        latitude = lat;
        longitude = longi;
    }

    @And("^I have post code (.+?) to check three hourly weather$")
    public void iHavePostCode(String pCode) {
        postCode = pCode;
    }

    @And("^I do GET on (.+?) for state code$")
    public void iGetOnEndPoint(String endPoint) {
        resp = invokeUtilObj.getWeatherByPostCode(endPoint, latitude, longitude, key);
    }

    @And("^I do GET on (.+?) for weather report$")
    public void getWeatherReport(String endPoint) {
        resp = invokeUtilObj.getThreeHourlyWeather(endPoint, postCode, key);
    }

    @Then("^I receive the weather forecast with state code as (.+?)$")
    public void iGetStateCode(String stateCode) {
        String responseStateCode = resp.jsonPath().get("data[0].state_code");
        junit.framework.Assert.assertTrue(stateCode.equalsIgnoreCase(responseStateCode));
    }

    @Then("^I receive the utc time stamp$")
    public void iGetTimeStamp() {
        responseList = resp.jsonPath().getList("data");
        for (int i = 0; i < responseList.size(); i++) {
        	junit.framework.Assert.assertTrue(responseList.get(i).containsKey("timestamp_utc"));
            System.out.println("timestamp_utc for 3 hourly update: " + responseList.get(i).get("timestamp_utc"));
        }
    }

    @Then("^I receive the weather$")
    public void iGetWeather() {
        for (int i = 0; i < responseList.size(); i++) {
            junit.framework.Assert.assertTrue(responseList.get(i).containsKey("weather"));
            System.out.println("weather for 3 hourly update: " + "icon:" + resp.jsonPath().get("data[" + i + "].weather.icon")
                    + "  code: " + resp.jsonPath().get("data[" + i + "].weather.code")
                    + "  description: " + resp.jsonPath().get("data[" + i + "].weather.description"));
        }
    }
}