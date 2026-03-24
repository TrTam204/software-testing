package api.test;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;

import api.base.ApiBaseTest;
import static io.restassured.RestAssured.given;
public class UserApiTest extends ApiBaseTest {
        @Test
        public void testGetPosts() {
        given(requestSpec)
        .when()
                .get("/posts")
        .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("size()", greaterThan(0));
        }
        @Test
        public void testGetPostById() {
        given(requestSpec)
        .when()
                .get("/posts/1")
        .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", not(emptyString()));
        }
        @Test
        public void testGetComments() {
        given(requestSpec)
                .queryParam("postId", 1)
        .when()
                .get("/comments")
        .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("size()", greaterThan(0));
        }
        @Test
        public void testNotFound() {
        given(requestSpec)
        .when()
                .get("/posts/9999")
        .then()
                .statusCode(404);
        }
}