package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.shopkart.config.AppConfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void beforeScenario() {

        Configuration.browser = "chrome";

        Configuration.baseUrl = AppConfig.BASE_URL;

        Configuration.browserSize = "1920x1080";

        Configuration.headless = false;

        Configuration.timeout = 10000;

        Selenide.open("/");
    }

    @After
    public void afterScenario() {

        Selenide.closeWebDriver();
    }

}