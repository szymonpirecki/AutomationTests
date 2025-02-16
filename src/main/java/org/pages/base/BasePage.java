package org.pages.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait defaultWait;
    public static boolean isInIframe = false;

    public BasePage(WebDriver driver) {
        initPage(driver);
    }

    private void initPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.defaultWait = new WebDriverWait(driver,
                Duration.ofSeconds(Long.parseLong(System.getProperty("defaultWait"))));
        this.driver = driver;
    }

    protected void clickElement(WebElement element) {
        defaultWait.until(ExpectedConditions.visibilityOf(element))
                .click();
    }

    protected void switchToIframe(WebElement iframe) {
        driver.switchTo().frame(iframe);
        isInIframe = true;
        log.info("Switched to Iframe: {}", iframe);
    }
}