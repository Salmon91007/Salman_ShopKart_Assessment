package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.locators.XP;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    /**
     * Verify Login Page is displayed
     */
    public LoginPage verifyLoginPage() {

        $x(XP.EMAIL).shouldBe(Condition.visible);
        $x(XP.PASSWORD).shouldBe(Condition.visible);
        $x(XP.SIGN_IN).shouldBe(Condition.visible);

        return this;
    }


    public LoginPage enterEmail(String email) {

        $x(XP.EMAIL).shouldBe(Condition.visible).setValue(email);

        return this;
    }


    public LoginPage enterPassword(String password) {

        $x(XP.PASSWORD).shouldBe(Condition.visible).setValue(password);

        return this;
    }


    public HomePage clickSignIn() {

        $x(XP.SIGN_IN).shouldBe(Condition.enabled).click();

        return new HomePage();
    }


    public HomePage login(String email, String password) {

        enterEmail(email);
        enterPassword(password);
        return clickSignIn();
    }

}