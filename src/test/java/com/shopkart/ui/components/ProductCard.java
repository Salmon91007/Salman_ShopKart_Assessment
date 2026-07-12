package com.shopkart.ui.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProductCard {

    private final SelenideElement root;

    public ProductCard(SelenideElement root) {
        this.root = root;
    }

    public String getSku() {
        return root.$x(".//div[@class='product-meta']/span[2]").getText();
    }

    public String getProductName() {
        return root.$x(".//h2/button").getText();
    }

    public String getPrice() {
        return root.$x(".//div[@class='product-footer']/strong").getText();
    }

    public String getStock() {
        return root.$x(".//div[@class='product-footer']/span").getText();
    }

    public ProductCard verifyOutOfStock() {

        root.$x(".//span[contains(@class,'stock')]")
                .shouldHave(Condition.exactText("Out of stock"));

        root.$x(".//button[contains(@class,'quick-add')]")
                .shouldBe(Condition.disabled);

        return this;
    }

    public ProductCard addToCart() {
        root.$x(".//button[contains(@class,'quick-add')]")
                .shouldBe(Condition.enabled)
                .click();
        return this;
    }

    public ProductCard openProduct() {
        root.$x(".//h2/button").click();
        return this;
    }


    public boolean isOutOfStock() {
        return getStock().contains("Out of stock");
    }
}