package com.shopkart.stepDefs;

import com.codeborne.selenide.Selenide;
import com.shopkart.api.clientApis.AuthClient;
import com.shopkart.config.AppConfig;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.*;

import com.shopkart.api.clientApis.OrderClient;
import com.shopkart.data.repository.OrderRepository;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.*;

public class CheckoutSteps {

    private final WorldContext context;
    private final AuthClient authClient = new AuthClient();

    private final OrderClient orderClient = new OrderClient();
    private final OrderRepository orderRepository = new OrderRepository();

    public CheckoutSteps(WorldContext context) {
        this.context = context;

        context.setLoginPage(new LoginPage());
    }


    @Then("the Home page should be displayed")
    public void homePageDisplayed() {

        context.getHomePage()
                .verifyHomePage();
    }

    @When("{string} searches for the product {string}")
    public void searchProduct(String user, String product) {

        context.setProductName(product);

        context.getHomePage()
                .searchProduct(product);
    }

    @Then("the product {string} should be displayed in the search results")
    public void verifyProduct(String product) {

        context.getHomePage()
                .verifyProductVisible(product);
    }

    @When("{string} adds the product {string} to the cart")
    public void addProductToCart(String user, String product) {

        context.setProductName(product);

        context.getHomePage()
                .addProductToCart(product);
    }

    @When("{string} navigates to the Cart page")
    public void navigateToCart(String user) {

        CartPage cartPage = context.getHomePage()
                .openCart();

        context.setCartPage(cartPage);
    }

    @Then("the Cart page should be displayed")
    public void verifyCartPage() {

        context.getCartPage()
                .verifyCartPage();
    }

    @Then("the product {string} should be displayed in the cart")
    public void verifyProductInCart(String sku) {

        context.setSku(sku);

        context.getCartPage()
                .verifyProductInCart(sku);
    }

    @Then("the cart total should be {string}")
    public void verifyCartTotal(String total) {

        context.getCartPage()
                .verifyCartTotal(total);
    }

    @When("{string} proceeds to Checkout")
    public void proceedToCheckout(String user) {

        CheckoutPage checkoutPage = context.getCartPage()
                .clickCheckout();

        context.setCheckoutPage(checkoutPage);
    }

    @Then("the Checkout page should be displayed")
    public void verifyCheckoutPage() {

        context.getCheckoutPage()
                .verifyCheckoutPage();
    }

    @When("{string} enters the delivery address {string}")
    public void enterDeliveryAddress(String user,
                                     String address) {

        context.setDeliveryAddress(address);

        context.getCheckoutPage()
                .enterDeliveryAddress(address);
    }

    @When("{string} places the order")
    public void placeOrder(String user) {

        OrdersPage ordersPage = context.getCheckoutPage()
                .placeOrder();

        context.setOrdersPage(ordersPage);
    }

    @Then("the Order Confirmation page should be displayed")
    public void verifyOrderConfirmationPage() {

        context.getOrdersPage()
                .verifyOrderConfirmationPage();

        String orderText = context.getOrdersPage()
                .getOrderNumber();

        long orderId = Long.parseLong(
                orderText.replaceAll("\\D", "")
        );

        context.setOrderId(orderId);
    }

    @Then("the order status should be {string}")
    public void verifyOrderStatus(String status) {

        context.getOrdersPage()
                .verifyOrderStatus(status);
    }

    @Then("the order total should be {string}")
    public void verifyOrderTotal(String total) {

        context.getOrdersPage().verifyOrderTotal(total);
    }

    @Then("the delivery address should be {string}")
    public void verifyDeliveryAddress(String address) {

        context.getOrdersPage().verifyDeliveryAddress(address);
    }

    @Then("the Order API should return status {string}")
    public void verifyOrderApiStatus(String expectedStatus) {

        Response response = orderClient.getOrder(
                context.getToken(),
                String.valueOf(context.getOrderId())
        );

        context.setResponse(response);

        assertEquals(
                expectedStatus,
                response.jsonPath().getString("status")
        );
    }

    @Then("the Order API should return total amount {int} paise")
    public void verifyOrderApiTotal(int expectedTotal) {

        assertEquals(
                expectedTotal,
                context.getResponse()
                        .jsonPath()
                        .getInt("totalPaise")
        );
    }

    @Then("the database should contain {string} order for {string}")
    public void verifyDatabaseContainsOrder(String status,
                                            String user) {

        int count = orderRepository.countPlacedOrders(
                context.getCustomerId()
        );

        assertEquals(1, count);
    }

    @Then("the order should belong to {string}")
    public void verifyOrderOwner(String user) {

        long customerId = orderRepository.customerIdOfOrder(context.getOrderId());

        assertEquals(
                context.getCustomerId(),
                customerId
        );
    }

}