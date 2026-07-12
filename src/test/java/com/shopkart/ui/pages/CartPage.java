package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.locators.XP;

import static com.codeborne.selenide.Selenide.$x;

public class CartPage {


    public CartPage verifyCartPage() {

        $x(XP.CART_HEADING).shouldBe(Condition.visible);

        $x(XP.CART_CAPTION).shouldBe(Condition.visible);

        $x(XP.CART_TOTAL).shouldBe(Condition.visible);

        return this;
    }


    public CartPage verifyProductInCart(String sku) {

        XP.cartLine(sku).shouldBe(Condition.visible);
        return this;
    }


    public CartPage verifyLineTotal(String sku, String expectedAmount) {

        XP.cartLine(sku).$x(XP.LINE_TOTAL).shouldHave(Condition.text(expectedAmount));
        return this;
    }


    public String getLineTotal(String sku) {
        return XP.cartLine(sku).$x(XP.LINE_TOTAL).shouldBe(Condition.visible).getText();
    }


    public CartPage verifyCartTotal(String expectedTotal) {

        $x(XP.CART_TOTAL).shouldHave(Condition.text(expectedTotal));

        return this;
    }


    public String getCartTotal() {

        return $x(XP.CART_TOTAL).shouldBe(Condition.visible).getText();
    }


    public HomePage continueShopping() {

        $x(XP.CONTINUE_SHOPPING).shouldBe(Condition.enabled).click();

        return new HomePage();
    }


    public CheckoutPage clickCheckout() {

        $x(XP.CHECKOUT).shouldBe(Condition.enabled).click();

        return new CheckoutPage();
    }
}