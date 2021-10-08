package com.alex.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MortgagePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'teaser')]/div[@class='kit-grid kit-grid_fixed']//h1")
    private WebElement title;
    @FindBy(xpath = "//div[contains(text(),'Стоимость недвижимости')]//preceding-sibling::input")
    private WebElement priceInput;
    @FindBy(xpath = "//div[contains(text(),'Первоначальный взнос')]//preceding-sibling::input")
    private WebElement firstPaymentInput;
    @FindBy(xpath = "//div[contains(text(),'Срок кредита')]//preceding-sibling::input")
    private WebElement termInput;
    @FindBy(xpath = "//span[contains(text(),'Страхование жизни и здоровья')]/../following-sibling::span//input")
    private WebElement lifeInsuranceCheckBox;
    @FindBy(xpath = "//span[contains(text(),'Молодая семья')]/../following-sibling::span//input")
    private WebElement youngFamilyCheckBox;
    @FindBy(xpath = "//div[contains(@*,'result')]//span[contains(text(),'Ежемесячный платеж')]//following-sibling::span/span")
    private WebElement monthlyPayment;
    @FindBy(xpath = "//div[contains(@*,'result')]//span[contains(text(),'Сумма кредита')]//following-sibling::span/span")
    private WebElement creditAmount;
    @FindBy(xpath = "//div[contains(@*,'result')]//span[contains(text(),'Необходимый доход')]//following-sibling::span/span")
    private WebElement requiredIncome;
    @FindBy(xpath = "//div[contains(@*,'result')]//div[contains(@*,'hint')]//span[contains(text(),'Процентная ставка')]//following-sibling::span/span")
    private WebElement interestRate;


    public MortgagePage openCalculator() {
        driverManager.getDriver().switchTo().frame(driverManager.getDriver().findElement(By.xpath("//iframe[@id='iFrameResizer0']")));
        return this;
    }

    public MortgagePage fillField(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Стоимость недвижимости":
                waitUtilElementToBeClickable(priceInput).click();
                priceInput.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                priceInput.sendKeys(value);
                element = priceInput;
                break;
            case "Первоначальный взнос":
                scrollWithOffset(firstPaymentInput, 0, 100);
                waitUtilElementToBeClickable(firstPaymentInput).click();
                firstPaymentInput.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                firstPaymentInput.sendKeys(value);
                element = firstPaymentInput;
                break;
            case "Срок кредита":
                waitUtilElementToBeClickable(termInput).click();
                termInput.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                termInput.sendKeys(value);
                element = termInput;
                break;
            default:
                Assertions.fail("Поле " + nameField + "' отсутствует на странице");

        }
        wait.until(ExpectedConditions.attributeToBe(element, "value", value));
        Assertions.assertEquals(value, element.getAttribute("value")
                , "Поле " + nameField + " заполнено некорректно");
        return this;
    }

    public MortgagePage checkOrUncheckBox(String operation, String nameField) {
        String booleanFlag = operation.equals("Поставить") ? "true" : "false";
        WebElement element = null;
        switch (nameField) {
            case "Молодая семья":
                element = youngFamilyCheckBox;
                break;
            case "Страхование жизни и здоровья":
                element = lifeInsuranceCheckBox;
                break;
            default:
                Assertions.fail("CheckBox  " + nameField + " отсутствует на странице");

        }
        scrollWithOffset(element, 0, 100);
        waitUtilElementToBeClickable(element.findElement(By.xpath("./..")));
        if (!element.getAttribute("aria-checked").equals(booleanFlag))
            element.click();
        if (!element.getAttribute("aria-checked").equals(booleanFlag))
            element.findElement(By.xpath("./..")).click();
        wait.until(ExpectedConditions.attributeToBe(element, "aria-checked", booleanFlag));
        Assertions.assertEquals(booleanFlag, element.getAttribute("aria-checked"), "CheckBox '" + nameField + "' не поставился");
        return this;
    }

    public MortgagePage checkFilledValues(String nameField, String target) {
        WebElement element = null;
        switch (nameField) {
            case "Ежемесячный платеж":
                element = monthlyPayment;
                break;
            case "Сумма кредита":
                element = creditAmount;
                break;
            case "Необходимый доход":
                element = requiredIncome;
                break;
            case "Процентная ставка":
                element = interestRate;
                break;
            default:
                Assertions.fail("Поле " + nameField + " отсутствует на странице");

        }

        Assertions.assertEquals(Double.parseDouble(target.replaceAll("[^\\d]", "")),
                Double.parseDouble(element.getText().replaceAll("[^,\\d]", "").replace(",", ".")),
                "Проверка поля " + nameField + " не пройдена");
        return this;
    }

    public void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
        }
    }
}
