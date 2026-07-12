package com.shopkart.stepDefs;

import com.codeborne.selenide.Selenide;
import com.shopkart.api.clientApis.AuthClient;
import com.shopkart.api.clientApis.CartClient;
import com.shopkart.config.AppConfig;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class CommonSteps {

    private final WorldContext context;

    private final AuthClient authClient = new AuthClient();
    private final CartClient cartClient = new CartClient();

    public CommonSteps(WorldContext context) {
        this.context = context;
    }

    @Given("{string} opens the ShopKart login page")
    public void openLoginPage(String user) {

        Selenide.open(AppConfig.BASE_URL + "/login");

        context.setCurrentUser(user);

        context.setLoginPage(
                new LoginPage()
                        .verifyLoginPage()
        );
    }


    @Given("the ShopKart application is available")
    public void shopKartApplicationIsAvailable() {

        Selenide.open(AppConfig.BASE_URL + "/login");

        context.setLoginPage(new LoginPage().verifyLoginPage()
        );
    }


    @Given("{string} logs into ShopKart successfully")
    public void userLogsIntoShopKartSuccessfully(String user) {

        String email = Secrets.email(user);
        String password = Secrets.password(user);

        context.setCurrentUser(user);

        HomePage homePage = context.getLoginPage()
                .login(email, password);

        context.setHomePage(homePage);

        // Store API Authentication for later validations
        context.setToken(
                authClient.token(email, password)
        );

        context.setCustomerId(
                authClient.customerId(email, password)
        );
    }


    @Given("{string} authenticates through the API")
    public void authenticateThroughApi(String user) {

        String email = Secrets.email(user);
        String password = Secrets.password(user);

        context.setCurrentUser(user);

        context.setToken(
                authClient.token(email, password)
        );

        context.setCustomerId(
                authClient.customerId(email, password)
        );
    }


    @Given("she creates a new shopping cart")
    public void createShoppingCart() {

        Response response =
                cartClient.createCart(
                        context.getToken()
                );

        context.setResponse(response);

        long cartId = response
                .jsonPath()
                .getLong("cartId");

        context.setCartId(cartId);
    }

    @When("she adds {int} quantities of {string}")
    public void addItemToCart(int quantity, String sku) {

        Response response = cartClient.addItem(
                context.getToken(),
                String.valueOf(context.getCartId()),
                sku,
                quantity);

        context.setResponse(response);
        context.setResponseStatus(response.statusCode());
    }
}