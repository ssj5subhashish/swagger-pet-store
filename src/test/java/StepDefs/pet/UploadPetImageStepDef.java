package StepDefs.pet;

import StepDefs.BaseStepDef;
import cucumber.api.java.en.When;
import util.Randomizer;
import util.ScenarioContext;

import static endpoints.PetControllerEndpoints.uploadImage;

public class UploadPetImageStepDef extends BaseStepDef {
    public UploadPetImageStepDef(ScenarioContext context, Randomizer randomizer) {
        super(context, randomizer);
    }

    @When("^I try to upload a pet image with id \"([^\"]*)\" and metaData \"([^\"]*)\" and the file \"([^\"]*)\"$")
    public void ITryToUploadAPetImageWithIdAndMetaDataAndTheFile(String id, String metaData, String filePath){
        filePath = System.getProperty("user.dir")+"/src/test/resources/files/"+filePath;

        context.response = uploadImage(Long.valueOf(randomizer.get(id)),randomizer.get(metaData),filePath);
    }
}
