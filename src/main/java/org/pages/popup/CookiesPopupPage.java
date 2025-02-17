package org.pages.popup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.base.BasePage;

public class CookiesPopupPage extends BasePage {

    public CookiesPopupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button#onetrust-accept-btn-handler")
    private WebElement acceptCookiesBtn;

    public void acceptCookies() {
        clickElement(acceptCookiesBtn);
    }
}