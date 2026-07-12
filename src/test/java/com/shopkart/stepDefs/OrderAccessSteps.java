package com.shopkart.stepDefs;

import com.shopkart.api.clientApis.AuthClient;
import com.shopkart.api.clientApis.CartClient;
import com.shopkart.api.clientApis.OrderClient;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.WorldContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

public class OrderAccessSteps {

    private final WorldContext context;

    private final AuthClient authClient = new AuthClient();
    private final CartClient cartClient = new CartClient();
    private final OrderClient orderClient = new OrderClient();

    public OrderAccessSteps(WorldContext context) {
        this.context = context;
    }

    @Given("{string} owns a PLACED order")
    public void userOwnsAPlacedOrder(String user) {

        // Login as Alice
        String email = Secrets.email(user);
        String password = Secrets.password(user);

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
        Response orderResponse = orderClient.placeOrder(
                token,
                cartId
        );

        long orderId = orderResponse
                .jsonPath()
                .getLong("orderId");

        context.setOrderId(orderId);
    }

    @When("{string} requests Alice's order")
    public void anotherCustomerRequestsOrder(String user) {

        // Login as Bob
        String email = Secrets.email(user);
        String password = Secrets.password(user);

        String bobToken = authClient.token(email, password);

        Response response = orderClient.getOrder(
                bobToken,
                String.valueOf(context.getOrderId())
        );

        context.setResponse(response);
        context.setResponseStatus(response.statusCode());
    }

    @Then("the Order API should respond with {int}")
    public void verifyForbiddenStatus(int expectedStatus) {

        assertEquals(
                expectedStatus,
                context.getResponseStatus()
        );
    }

    @Then("the response body should not contain order details")
    public void verifyResponseBody() {

        String body = context.getResponse().asString();

        assertFalse(body.contains("orderId"));
        assertFalse(body.contains("status"));
        assertFalse(body.contains("totalPaise"));
        assertFalse(body.contains("customerId"));
    }
}