package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.locators.XP;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {


    public HomePage verifyHomePage() {

        $x(XP.SEARCH_BOX).shouldBe(Condition.visible);
        $x(XP.CATALOG_BUTTON).shouldBe(Condition.visible);

        return this;
    }


    public HomePage searchProduct(String productName) {

        $x(XP.SEARCH_BOX)
                .shouldBe(Condition.visible)
                .clear();

        $x(XP.SEARCH_BOX)
                .setValue(productName);

        $x(XP.SEARCH_)
                .shouldBe(Condition.enabled)
                .click();

        return this;
    }


    public HomePage verifyProductVisible(String productName) {

        XP.product(productName)
                .shouldBe(Condition.visible);

        return this;
    }


    public HomePage openProduct(String productName) {

        $x(String.format(XP.PRODUCT, productName))
                .shouldBe(Condition.visible)
                .click();

        return this;
    }


    public HomePage addProductToCart(String productName) {

        XP.addToCart(productName)
                .shouldBe(Condition.visible)
                .click();

        return this;
    }

    public CartPage openCart() {

        $x(XP.CART_BUTTON)
                .shouldBe(Condition.visible)
                .click();

        return new CartPage();
    }


    public HomePage openApiDocs() {

        $x(XP.API_DOCS_LINK).shouldBe(Condition.visible).click();

        return this;
    }


    public HomePage verifyLoggedInUser(String userName) {

        $x(XP.LOGGED_IN_USER).shouldHave(Condition.text(userName));

        return this;
    }


    public LoginPage signOut() {

        $x(XP.SIGN_OUT_BUTTON).shouldBe(Condition.visible).click();

        return new LoginPage();
    }

}