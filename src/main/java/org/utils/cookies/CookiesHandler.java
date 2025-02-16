package org.utils.cookies;

import org.openqa.selenium.WebDriver;
import org.pages.popup.CookiesPopupPage;

public class CookiesHandler {

    public static void handleCookiesPopup(WebDriver driver){
        new CookiesPopupPage(driver)
                .acceptCookies();
    }
}