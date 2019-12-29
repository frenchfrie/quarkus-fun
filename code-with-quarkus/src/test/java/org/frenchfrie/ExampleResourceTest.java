package org.frenchfrie;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@Testcontainers
public class ExampleResourceTest {

  @Container
  private static final MariaDBContainer MARIA_DB_CONTAINER = new MariaDBContainer()
      .withDatabaseName("my-db")
      .withUsername("my-app")
      .withPassword("my-app");

  @Test
  public void testHelloEndpoint() {
    given()
        .when().get("/hello")
        .then()
        .statusCode(200)
        .body(is("hello"));
  }

}