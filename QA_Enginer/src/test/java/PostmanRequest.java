import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostmanRequest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGetRequestStatusCode() {
        Response response = given().when().get("/get?foo1=bar1&foo2=bar2");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testGetRequestResponseBody() {
        Response response = given().when().get("/get?foo1=bar1&foo2=bar2");
        String foo1 = response.jsonPath().getString("args.foo1");
        String foo2 = response.jsonPath().getString("args.foo2");
        assertEquals("bar1", foo1);
        assertEquals("bar2", foo2);

    }

    @Test
    public void testPostRawTextStatusCode() {
        String requestBody = "{ \"test\": \"value\" }";
        Response response = given().body(requestBody).when().post("/post");
        assertEquals(200, response.getStatusCode());

    }

    @Test
    public void testPostRawTextResponseBody() {
        String requestBody = "{ \"test\": \"value\" }";
        Response response = given().body(requestBody).when().post("/post");
        String data = response.jsonPath().getString("data");
        assertEquals(requestBody, data);
    }

    @Test
    public void testPostFormDataStatusCode() {
        Response response = given()
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when().post("/post");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testPostFormDataResponseBody() {
        Response response = given()
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when().post("/post");
        assertEquals("bar1", response.jsonPath().getString("form.foo1"));
        assertEquals("bar2", response.jsonPath().getString("form.foo2"));
    }

    @Test
    public void testPutRequestStatusCode() {
        String requestBody = "This is expected to be sent back as part of response body.";
        Response response = given().body(requestBody).when().put("/put");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testPatchRequestStatusCode() {
        String requestBody = "This is expected to be sent back as part of response body.";
        Response response = given().body(requestBody).when().patch("/patch");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testDeleteRequestStatusCode() {
        String requestBody = "This is expected to be sent back as part of response body.";
        Response response = given().body(requestBody).when().delete("/delete");
        assertEquals(200, response.getStatusCode());
    }

}
