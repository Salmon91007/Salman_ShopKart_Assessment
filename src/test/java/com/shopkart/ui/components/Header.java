package com.shopkart.ui.components;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.locators.XP;
import com.shopkart.ui.pages.CartPage;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.LoginPage;

import static com.codeborne.selenide.Selenide.$x;

public class Header {

    public Header verifyHeader() {

        $x(XP.CATALOG_BUTTON).shouldBe(Condition.visible);
        $x(XP.CART_BUTTON).shouldBe(Condition.visible);
        $x(XP.API_DOCS_LINK).shouldBe(Condition.visible);
        $x(XP.LOGGED_IN_USER).shouldBe(Condition.visible);
        $x(XP.SIGN_OUT_BUTTON).shouldBe(Condition.visible);

        return this;
    }


    public HomePage goToCatalog() {

        $x(XP.CATALOG_BUTTON).shouldBe(Condition.enabled).click();

        return new HomePage();
    }


    public CartPage goToCart() {

        $x(XP.CART_BUTTON).shouldBe(Condition.enabled).click();

        return new CartPage();
    }


    public Header openApiDocs() {

        $x(XP.API_DOCS_LINK).shouldBe(Condition.enabled).click();

        return this;
    }


    public Header verifyLoggedInUser(String expectedUser) {

        $x(XP.LOGGED_IN_USER).shouldHave(Condition.exactText(expectedUser));

        return this;
    }


    public String getLoggedInUser() {

        return $x(XP.LOGGED_IN_USER).shouldBe(Condition.visible).getText();
    }


    public LoginPage signOut() {

        $x(XP.SIGN_OUT_BUTTON).shouldBe(Condition.enabled).click();

        return new LoginPage();
    }
}