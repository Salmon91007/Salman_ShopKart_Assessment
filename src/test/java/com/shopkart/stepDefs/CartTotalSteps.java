package com.shopkart.stepDefs;

import com.shopkart.api.clientApis.AuthClient;
import com.shopkart.api.clientApis.CartClient;
import com.shopkart.data.repository.CartRepository;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.WorldContext;

import io.cucumber.java.en.*;

import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTotalSteps {

    private final WorldContext context;

    private final AuthClient authClient = new AuthClient();
    private final CartClient cartClient = new CartClient();
    private final CartRepository cartRepository = new CartRepository();

    public CartTotalSteps(WorldContext context) {
        this.context = context;
    }


    @Then("the Cart API should return the correct total of {int}")
    public void verifyCartApiTotal(int expectedTotal) {

        Response response =
                cartClient.getCart(
                        context.getToken(),
                        String.valueOf(context.getCartId()));

        context.setResponse(response);

        int actualTotal =
                response.jsonPath()
                        .getInt("totalPaise");

        assertEquals(
                expectedTotal,
                actualTotal);
    }

    @Then("the database should contain the same cart total")
    public void verifyDatabaseCartTotal() {

        int apiTotal =
                context.getResponse()
                        .jsonPath()
                        .getInt("totalPaise");

        int databaseTotal =
                cartRepository.getCartTotal(
                        context.getCartId());

        assertEquals(
                apiTotal,
                databaseTotal);
    }

}