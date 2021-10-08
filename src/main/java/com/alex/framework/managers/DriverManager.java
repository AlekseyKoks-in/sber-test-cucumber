package com.alex.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static com.alex.framework.utils.PropConst.*;


public class DriverManager {

    private WebDriver driver;
    private static DriverManager driverManager = null;
    private final TestPropManager props = TestPropManager.getTestPropManager();

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (driverManager == null) {
            driverManager = new DriverManager();
        }
        return driverManager;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void initDriver() {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", props.getProperty(PATH_GECKO_DRIVER_WINDOWS));
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(PATH_CHROME_DRIVER_WINDOWS));
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "remote":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("81.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://161.35.194.216:4444/wd/hub").toURL(),
                            capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
        }
    }

}
