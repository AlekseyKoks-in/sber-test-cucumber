package com.alex.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/scenarios"},
        glue = {"com/alex/framework/steps"},
        tags = "@mortgageTest",
        plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}
)
public class CucumberRunner {
}
