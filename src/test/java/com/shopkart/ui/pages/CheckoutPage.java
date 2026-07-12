package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.locators.XP;

import static com.codeborne.selenide.Selenide.$x;

public class CheckoutPage {

    public CheckoutPage verifyCheckoutPage() {

        $x(XP.CHECKOUT_HEADING).shouldBe(Condition.visible);

        $x(XP.ADDRESS).shouldBe(Condition.visible);

        $x(XP.PLACE_ORDER).shouldBe(Condition.visible);

        return this;
    }

    /**
     * Enter Delivery Address
     */
    public CheckoutPage enterDeliveryAddress(String address) {

        $x(XP.ADDRESS).shouldBe(Condition.visible).clear();

        $x(XP.ADDRESS).setValue(address);

        return this;
    }


    public OrdersPage placeOrder() {

        $x(XP.PLACE_ORDER).shouldBe(Condition.enabled).click();

        return new OrdersPage();
    }


    public OrdersPage checkout(String address) {

        enterDeliveryAddress(address);
        return placeOrder();
    }
}