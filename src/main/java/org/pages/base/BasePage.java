package org.pages.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait defaultWait;
    protected Actions actions;
    public static boolean isInIframe = false;

    public BasePage(WebDriver driver) {
        initPage(driver);
    }

    private void initPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.actions = new Actions(driver);
        this.defaultWait = new WebDriverWait(driver,
                Duration.ofSeconds(Long.parseLong(System.getProperty("defaultWait"))));
        this.driver = driver;
    }

    protected void hooverOnElement(WebElement element) {
        defaultWait.until(ExpectedConditions.visibilityOf(element));
        actions
                .click()
                .pause(Duration.ofSeconds(Long.parseLong(System.getProperty("pause"))))
                .moveToElement(element)
                .perform();
    }

    protected void clickElement(WebElement element) {
        defaultWait.until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    protected void fillInput(WebElement field, String input) {
        field.clear();
        field.sendKeys(input);
        log.info("Filled {} field with '{}' value", field, input);
    }

    protected void checkBox(WebElement box) {
        if (box.isSelected()) {
            log.info("Checkbox {} is already selected", box.getText());
            return;
        }
        clickElement(box);
        log.info("Checked: {}", box.getText());
    }

    protected void switchToIframe(WebElement iframe) {
        defaultWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        isInIframe = true;
        log.info("Switched to Iframe: {}", iframe);
    }
}