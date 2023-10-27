package endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;

import static io.restassured.RestAssured.given;

/**
 *
 * @author Subhashish Behra <ssj5subhashish@gmail.com>
 */
public class PetControllerEndpoints {
    private static final String APPLICATION_JSON = "application/json";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String API_KEY = "temp_key";

    public static Response uploadImage(Long petId, String additionalData, String filePath){
        RequestSpecification req = given().contentType(MULTIPART_FORM_DATA).accept(APPLICATION_JSON);
        if (petId != null){
            req.pathParam("petId",petId);
        }
        if (additionalData != null){
            req.formParam("additionalMetadata",additionalData);
        }
        if (filePath != null){
            req.multiPart("file", new File(filePath), "image/jpeg");
        }
        req.log().all();
        return req.post("/pet/{petId}/uploadImage");
    }

    public static Response addNewPet(String addNewPetJson){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        if (addNewPetJson != null){
            req.body(addNewPetJson);
        }
        return req.post("/pet");
    }

    public static Response updateExistingPet(String updateExistingPetJson){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        if (updateExistingPetJson != null){
            req.body(updateExistingPetJson);
        }
        return req.put("/pet");
    }

    public static Response findPetByStatus(String petStatus){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        if(petStatus != null){
            req.queryParam("status",petStatus);
        }
        return req.get("/pet/findByStatus");
    }

    public static Response findPetById(Long petId){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        if(petId != null){
            req.pathParam("petId",petId);
        }
        return req.get("/pet/{petId}");
    }

    public static Response updatePetInStore(Long petId, String name, String status){
        RequestSpecification req = given().contentType(FORM_URLENCODED).accept(APPLICATION_JSON);
        if(petId != null){
            req.formParam("name",name);
            req.formParam("status",status);
        }
        return req.post("/pet/{petId}");
    }

    public static Response deletePet(Long petId){
        RequestSpecification req = given().accept(APPLICATION_JSON).header("api_key",API_KEY);
        if(petId != null){
            req.pathParam("petId",petId);
        }
        return req.delete("/pet/{petId}");
    }
}
