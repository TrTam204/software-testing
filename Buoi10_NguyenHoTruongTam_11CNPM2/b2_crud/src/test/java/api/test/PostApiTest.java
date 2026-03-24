package api.test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;

import api.base.ApiBaseTest;
import api.model.Post;
import static io.restassured.RestAssured.given;
public class PostApiTest extends ApiBaseTest {
        @Test
        public void testCreatePost() {
        Post post = new Post(1, "Test Title", "Test Body");

        given(requestSpec)
                .body(post)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .body("id", notNullValue());
        }
        @Test
        public void testGetPost() {
        given(requestSpec)
        .when()
                .get("/posts/1")
        .then()
                .statusCode(200)
                .body("id", equalTo(1));
        }
        @Test
        public void testUpdatePost() {
        Post updatePost = new Post(1, "Updated Title", "Updated Body");

        given(requestSpec)
                .body(updatePost)
        .when()
                .put("/posts/1")
        .then()
                .statusCode(200); 
        }
        @Test
        public void testDeletePost() {
        given(requestSpec)
        .when()
                .delete("/posts/1")
        .then()
                .statusCode(200);
        }
}