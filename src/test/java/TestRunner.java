import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber.json"}, tags = {"~@ignore"})
public class TestRunner {

    @BeforeClass
    public static void setup() {
        configureBaseUri();
        configureRestAssuredLogging();
    }

    private static void configureBaseUri(){
        String baseUriProperty = System.getProperty("sys.base.uri");
        if (baseUriProperty != null && !baseUriProperty.isEmpty()){
            RestAssured.baseURI = baseUriProperty;
        }
    }

    private static void configureRestAssuredLogging() {
        if (Boolean.parseBoolean(System.getProperty("sys.log"))) {
            addRequestAndResponseFilters();
        } else {
            //only log if validation fails
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        }
    }

    private static void addRequestAndResponseFilters() {
        //Enable global logging for request and response if validation fails
        List<Filter> restAssuredFilters = new ArrayList<>(RestAssured.filters());
        restAssuredFilters.add(new RequestLoggingFilter());
        restAssuredFilters.add(new ResponseLoggingFilter());
        RestAssured.filters(restAssuredFilters);
    }

}