package com.shopkart;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")

@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.shopkart.stepDefs"
)

@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value =
                "pretty," +
                        "summary," +
                        "html:build/reports/cucumber/cucumber.html," +
                        "json:build/reports/cucumber/cucumber.json," +
                        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)

@ConfigurationParameter(
        key = FILTER_TAGS_PROPERTY_NAME,
        value = "@smoke"
)

public class RunCucumberTest {
}