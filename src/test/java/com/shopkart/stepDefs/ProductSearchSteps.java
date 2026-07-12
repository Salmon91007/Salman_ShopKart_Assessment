package com.shopkart.stepDefs;

import com.shopkart.api.clientApis.ProductClient;
import com.shopkart.support.WorldContext;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProductSearchSteps {

    private final WorldContext context;
    private final ProductClient productClient = new ProductClient();

    public ProductSearchSteps(WorldContext context) {
        this.context = context;
    }

    @When("{string} searches for {string}")
    public void userSearchesForProduct(String user, String productName) {

        context.setProductName(productName);

        context.getHomePage()
                .searchProduct(productName);
    }

    @Then("the product {string} should appear in the catalog")
    public void verifyProductDisplayed(String productName) {

        context.getHomePage()
                .verifyProductVisible(productName);
    }

    @Then("the Product Search API should return {string}")
    public void verifyProductSearchApi(String expectedProduct) {

        Response response = productClient.search(expectedProduct);

        context.setResponse(response);

        assertEquals(200, response.statusCode());

        List<Map<String, Object>> products =
                response.jsonPath().getList("");

        assertFalse(products.isEmpty(), "Product search returned no results.");

        boolean productFound = products.stream()
                .anyMatch(product ->
                        expectedProduct.equals(product.get("name")));

        assertTrue(
                productFound,
                "Expected product '" + expectedProduct + "' was not returned by the API."
        );
    }

    @Then("the Product API should return SKU {string}")
    public void verifyProductSku(String expectedSku) {

        Response response = context.getResponse();

        List<Map<String, Object>> products =
                response.jsonPath().getList("");

        String actualSku = products.stream()
                .filter(product ->
                        context.getProductName().equals(product.get("name")))
                .map(product -> product.get("sku").toString())
                .findFirst()
                .orElse(null);

        assertNotNull(actualSku, "SKU was not found for product: " + context.getProductName());

        assertEquals(expectedSku, actualSku);
    }
}