package com.alex.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import com.alex.framework.managers.PageManager;

public class MortgagePageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Перейти к расчету ипотеки$")
    public void openMortgageCalculator() {
        pageManager.getMortgagePage().openCalculator();
    }

    @И("^Заполнить поля формы:$")
    public void fillFields(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortgagePage().fillField((String) key, (String) value));
    }

    @И("^(Поставить|Убрать) галочку \"(.+)\"$")
    public void checkBox(String operation, String checkBoxName) {
        pageManager.getMortgagePage().checkOrUncheckBox(operation,checkBoxName);
    }

    @И("^Подождать загрузку данных")
    public void sleep() {
        pageManager.getMortgagePage().sleep();
    }

    @И("^Проверить значение полей")
    public void checkFilledFields(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortgagePage().checkFilledValues((String) key, (String) value));
    }
}
