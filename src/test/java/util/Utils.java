package util;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class Utils {

    public static void assertResponseJsonFields(Response response, Map<String, String> responseFields) {
        JsonPath jsonPath = response.then().extract().jsonPath();

        responseFields.entrySet().forEach((field) -> {
            if (StringUtils.isNumeric(field.getValue())) {
                assertThat(jsonPath.get(field.getKey()), is(Integer.parseInt(field.getValue())));
            } else {
                assertThat(jsonPath.get(field.getKey()), is(field.getValue()));
            }
        });
    }

    public static void assertResponseJsonFields(Response response, Map<String, String> responseFields, Randomizer randomizer) {
        JsonPath jsonPath = response.then().extract().jsonPath();
        assertFields(jsonPath, responseFields, randomizer);
    }

    public static void assertResponseJsonFields(String json, Map<String, String> responseFields, Randomizer randomizer) {
        JsonPath jsonPath = new JsonPath(json);
        assertFields(jsonPath, responseFields, randomizer);
    }

    private static void assertFields(JsonPath jsonPath, Map<String, String> responseFields, Randomizer randomizer) {
        responseFields.entrySet().forEach((field) -> {
            if (StringUtils.isNumeric(field.getValue())) {
                assertThat(jsonPath.get(field.getKey()), is(Integer.parseInt(randomizer.get(field.getValue()))));
            } else if (field.getValue().equals("true") || field.getValue().equals("false")) {
                assertThat(jsonPath.get(field.getKey()), is(Boolean.parseBoolean(field.getValue())));
            } else if (field.getValue().equals("null")) {
                assertThat(jsonPath.get(field.getKey()), isEmptyOrNullString());
            } else {
                assertThat(jsonPath.get(field.getKey()), is(randomizer.get(field.getValue())));
            }
        });
    }

    //For invoking the assertFields method through response body as map
    public static void assertResponseJsonFields(Map<String, String> json_response, Map<String, String> responseFields, Randomizer randomizer) {
        assertFields(json_response, responseFields, randomizer);
    }

    //For asserting response body as map
    private static void assertFields(Map<String, String> jsonPath, Map<String, String> responseFields, Randomizer randomizer) {
        responseFields.entrySet().forEach((field) -> {
            if (StringUtils.isNumeric(field.getValue())) {
                assertThat(jsonPath.get(field.getKey()), is(Integer.parseInt(randomizer.get(field.getValue()))));
            } else if (field.getValue().equals("true") || field.getValue().equals("false")) {
                assertThat(jsonPath.get(field.getKey()), is(Boolean.parseBoolean(field.getValue())));
            } else if (field.getValue().equals("null")) {
                assertThat(jsonPath.get(field.getKey()), isEmptyOrNullString());
            } else {
                assertThat(jsonPath.get(field.getKey()), is(randomizer.get(field.getValue())));
            }
        });
    }

    public static File createTempFile(String fileName) throws IOException {
        final File file = Files.createTempFile(fileName, ".pdf").toFile();
        file.deleteOnExit();
        return file;
    }

    public static CharSequence getCurrentFormattedDateISO() {
        return DateTimeFormatter.ISO_INSTANT.format(ZonedDateTime.now());
    }
}
