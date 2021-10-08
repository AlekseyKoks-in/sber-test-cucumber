package com.alex.framework.managers;

import com.alex.framework.pages.HomePage;
import com.alex.framework.pages.MortgagePage;


public class PageManager {

    private static PageManager pageManager;
    private HomePage homePage;
    private MortgagePage mortgagePage;

    private PageManager() {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public MortgagePage getMortgagePage() {
        if (mortgagePage == null) {
            mortgagePage = new MortgagePage();
        }
        return mortgagePage;
    }

}
