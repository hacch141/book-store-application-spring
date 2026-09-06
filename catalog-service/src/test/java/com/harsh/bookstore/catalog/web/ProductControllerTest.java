package com.harsh.bookstore.catalog.web;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.harsh.bookstore.catalog.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

class ProductControllerTest extends AbstractIntegrationTest {

    @Test
    void shouldReturnFirstPageOfProducts() {
        given().when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(15))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldReturnProductByCode() {
        given().when()
                .get("/api/products/{code}", "P100")
                .then()
                .statusCode(200)
                .body("code", is("P100"))
                .body("name", is("The Hunger Games"))
                .body("price", is(34.0f));
    }

    @Test
    void shouldReturnNotFoundForUnknownCode() {
        given().when()
                .get("/api/products/{code}", "UNKNOWN")
                .then()
                .statusCode(404)
                .body("title", is("Product Not Found"))
                .body("status", is(404))
                .body("detail", is("Product with code UNKNOWN not found"));
    }
}
