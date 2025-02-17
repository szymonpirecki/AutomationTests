package org.pages.common;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.base.BasePage;
import org.pages.form.FormPage;

@Slf4j
public class HeaderPage extends BasePage {

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='menu-title' and text()='Get Started']")
    private WebElement getStarted;

    @FindBy(xpath = "//span[@class='menu-title' and text()='Trial']")
    private WebElement trial;

    public FormPage navigateToTrialForm() {
        hooverOnElement(getStarted);
        clickElement(trial);

        return new FormPage(driver);
    }
}