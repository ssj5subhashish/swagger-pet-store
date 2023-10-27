package endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class StoreControllerEndpoints {
    private static final String APPLICATION_JSON = "application/json";
    private static final String API_KEY = "temp_key";

    public static Response placeOrderForPet(String placeOrderForPetJson){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        if(placeOrderForPetJson != null){
            req.body(placeOrderForPetJson);
        }
        return req.post("/store/order");
    }

    public static Response findPurchaseOrderById(Long orderId){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        if(orderId != null){
            req.pathParam("orderId",orderId);
        }
        return req.get("/store/order/{orderId}");
    }

    public static Response deletePurchaseOrderById(Long orderId){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        if(orderId != null){
            req.pathParam("orderId",orderId);
        }
        return req.delete("/store/order/{orderId}");
    }

    public static Response getPetInventoryByStatus(){
        RequestSpecification req = given().accept(APPLICATION_JSON).header("api_key",API_KEY);
        return req.get("/store/inventory");
    }
}
