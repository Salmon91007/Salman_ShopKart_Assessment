package com.shopkart.stepDefs;

import com.shopkart.api.clientApis.AuthClient;
import com.shopkart.api.clientApis.CartClient;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.WorldContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutOfStockSteps {

    private final WorldContext context;

    private final AuthClient authClient = new AuthClient();
    private final CartClient cartClient = new CartClient();

    public OutOfStockSteps(WorldContext context) {
        this.context = context;
    }

    @Then("the Cart API should respond with {int}")
    public void verifyConflictStatus(int expectedStatusCode) {

        assertEquals(
                expectedStatusCode,
                context.getResponseStatus()
        );
    }

}