package StepDefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import util.Randomizer;
import util.ScenarioContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class ReusableStepDef extends BaseStepDef{
    public ReusableStepDef(ScenarioContext context, Randomizer randomizer) {
        super(context, randomizer);
    }

    @Then("^I should get a (\\d+) response code$")
    public void iShouldGetAResponseCode(int statusCode) {
        context.response.then().statusCode(is(statusCode));
    }

    @And("^The \"([^\"]*)\" of the response body should contain \"([^\"]*)\"$")
    public void theMessageOfTheResponseBodyShouldContain(String path, String expectedMessage) throws Throwable {
        context.response.then().body(path,containsString(randomizer.get(expectedMessage)));
    }
}
