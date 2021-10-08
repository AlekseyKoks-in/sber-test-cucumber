package com.alex.framework.steps;

import com.alex.framework.managers.DriverManager;
import com.alex.framework.managers.InitManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;


public class Hooks {

    @Before
    public void initFramework() {
        InitManager.initFramework();
    }


    public void getScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getInstance().getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screen.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @After
    public void quitFramework(Scenario scenario) {
        getScreenshot(scenario);
        InitManager.quitFramework();
    }
}
