package endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserControllerEndpoints {
    private static final String APPLICATION_JSON = "application/json";

    public static Response createUsersWithArray(String listOfUsersJson){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        if(listOfUsersJson != null){
            req.body(listOfUsersJson);
        }
        return req.post("/user/createWithArray");
    }

    public static Response createUsersWithList(String listOfUsersJson){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        if(listOfUsersJson != null){
            req.body(listOfUsersJson);
        }
        return req.post("/user/createWithList");
    }

    public static Response getUserByName(String username){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        if(username != null){
            req.pathParam("username",username);
        }
        return req.get("/user/{username}");
    }

    public static Response updateUser(String username, String updateUserJson){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        if (username != null && updateUserJson != null){
            req.pathParam("username",username);
            req.body(updateUserJson);
        }
        return req.put("/user/{username}");
    }

    public static Response deleteUser(String username){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        if (username != null){
            req.pathParam("username",username);
        }
        return req.delete("/user/{username}");
    }

    public static Response getUserLogin(String username, String password){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        if(username != null && password != null){
            req.queryParam("username",username);
            req.queryParam("password",password);
        }
        return req.get("/user/login");
    }

    public static Response getUserLogout(){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        return req.get("/user/logout");
    }

    public static Response createUser(String createUserJson){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        if(createUserJson != null){
            req.body(createUserJson);
        }
        return req.post("/user");
    }
}
