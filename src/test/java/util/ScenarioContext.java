package util;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    public Response response;
    public Map<String, Object> vars = new HashMap<String, Object>();
}
