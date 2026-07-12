package com.shopkart.stepDefs;

import com.codeborne.selenide.Selenide;
import com.shopkart.config.AppConfig;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final WorldContext context;

    public LoginSteps(WorldContext context) {
        this.context = context;
    }



    @When("she enters valid credentials")
    public void sheEntersValidCredentials() {

        String user = context.getCurrentUser();

        context.getLoginPage()
                .enterEmail(Secrets.email(user))
                .enterPassword(Secrets.password(user));
    }

    @When("she clicks the Sign In button")
    public void sheClicksTheSignInButton() {

        HomePage homePage = context.getLoginPage()
                .clickSignIn();

        context.setHomePage(homePage);
    }

    @Then("she should be redirected to the Home page")
    public void sheShouldBeRedirectedToTheHomePage() {

        context.getHomePage()
                .verifyHomePage();
    }
}