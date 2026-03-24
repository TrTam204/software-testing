package api.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaTest {

    @Test
    public void testSchemaPost() {
        given()
        .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema/post-schema.json"));
    }
}