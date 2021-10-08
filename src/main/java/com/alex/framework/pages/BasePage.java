package com.alex.framework.pages;

import com.alex.framework.managers.DriverManager;
import com.alex.framework.managers.PageManager;
import com.alex.framework.managers.TestPropManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    protected final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getPageManager();
    protected Actions action = new Actions(driverManager.getDriver());
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
    private final TestPropManager props = TestPropManager.getTestPropManager();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(code, element, x, y);
        return element;
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void fillInputField(WebElement field, String value) {
        scrollToElementJs(field);
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(value);
    }

    protected void fillDateField(WebElement field, String value) {
        scrollToElementJs(field);
        field.sendKeys(value);
    }

}
