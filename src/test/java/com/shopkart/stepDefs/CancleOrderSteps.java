package com.shopkart.stepDefs;

import com.shopkart.api.clientApis.AuthClient;
import com.shopkart.api.clientApis.CartClient;
import com.shopkart.api.clientApis.OrderClient;
import com.shopkart.data.repository.OrderRepository;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.WorldContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CancleOrderSteps {

    private final WorldContext context;

    private final AuthClient authClient = new AuthClient();
    private final CartClient cartClient = new CartClient();
    private final OrderClient orderClient = new OrderClient();
    private final OrderRepository repository = new OrderRepository();

    public CancleOrderSteps(WorldContext context) {
        this.context = context;
    }

    @Given("Alice has placed an order")
    public void aliceHasPlacedAnOrder() {

        String email = Secrets.email("alice");
        String password = Secrets.password("alice");

        // Login
        String token = authClient.token(email, password);
        int customerId = authClient.customerId(email, password);

        context.setToken(token);
        context.setCustomerId(customerId);

        // Create Cart
        Response cartResponse = cartClient.createCart(token);

        String cartId = cartResponse.jsonPath().getString("cartId");


        context.setCartId(Long.parseLong(cartId));

        // Add Product
        cartClient.addItem(
                token,
                cartId,
                "SKU-BAG",
                2
        );

        // Place Order
        Response orderResponse =
                orderClient.placeOrder(
                        token,
                        cartId);

        long orderId =
                orderResponse.jsonPath()
                        .getLong("orderId");

        context.setOrderId(orderId);
    }

    @When("Alice cancels the order")
    public void cancelOrder() {

        Response response =
                orderClient.cancelOrder(
                        context.getToken(),
                        String.valueOf(context.getOrderId()));

        context.setResponse(response);
    }

    @Then("the order status should become {string}")
    public void verifyCancelled(String expectedStatus) {

        assertEquals(
                expectedStatus,
                repository.getOrderStatus(
                        context.getOrderId())
        );
    }

    @When("Alice cancels the same order again")
    public void cancelAgain() {

        Response response =
                orderClient.cancelOrder(
                        context.getToken(),
                        String.valueOf(context.getOrderId()));

        context.setResponseStatus(
                response.statusCode());
    }

    @Then("the response status should be {int}")
    public void verifyResponseStatus(int expectedStatus) {

        assertEquals(
                expectedStatus,
                context.getResponseStatus());
    }
}