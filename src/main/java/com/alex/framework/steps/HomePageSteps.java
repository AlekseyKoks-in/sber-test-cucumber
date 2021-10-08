package com.alex.framework.steps;

import io.cucumber.java.ru.И;
import com.alex.framework.managers.PageManager;

public class HomePageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Закрыть сообщение cookies$")
    public void closeCookiesDialog() {
        pageManager.getHomePage().closeCookiesDialog();
    }

    @И("^Выбрать раздел \"(.+)\" в главном меню$")
    public void selectBaseMenu(String nameMenu) {
        pageManager.getHomePage().selectBaseMenu(nameMenu);
    }

    @И("^Выбрать раздел \"(.+)\" в подменю главного меню$")
    public void selectSubMenu(String nameSubMenu) {
        pageManager.getHomePage().selectSubMenu(nameSubMenu);
    }

}
