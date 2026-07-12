package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.locators.XP;

import static com.codeborne.selenide.Selenide.$x;

public class OrdersPage {


    public OrdersPage verifyOrderConfirmationPage() {

        $x(XP.ORDER_NUMBER).shouldBe(Condition.visible);

        $x(XP.ORDER_STATUS).shouldBe(Condition.visible);

        $x(XP.ORDER_TOTAL).shouldBe(Condition.visible);

        return this;
    }


    public String getOrderNumber() {

        return $x(XP.ORDER_NUMBER)
                .shouldBe(Condition.visible)
                .getText();
    }


    public OrdersPage verifyOrderStatus(String expectedStatus) {

        $x(XP.ORDER_STATUS)
                .shouldHave(Condition.exactText(expectedStatus));

        return this;
    }


    public String getOrderStatus() {

        return $x(XP.ORDER_STATUS).shouldBe(Condition.visible).getText();
    }


    public OrdersPage verifyOrderTotal(String expectedTotal) {

        $x(XP.ORDER_TOTAL).shouldHave(Condition.text(expectedTotal));

        return this;
    }


    public String getOrderTotal() {

        return $x(XP.ORDER_TOTAL).shouldBe(Condition.visible).getText();
    }


    public OrdersPage verifyDeliveryAddress(String expectedAddress) {

        $x(XP.DELIVERY_ADDRESS).shouldHave(Condition.text(expectedAddress));

        return this;
    }


    public String getDeliveryAddress() {

        return $x(XP.DELIVERY_ADDRESS).shouldBe(Condition.visible).getText();
    }


    public HomePage returnToCatalog() {

        $x(XP.RETURN_TO_CATALOG).shouldBe(Condition.enabled).click();

        return new HomePage();
    }

}