package org.ui;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.pages.base.BasePage;
import org.utils.config.ConfigHandler;
import org.utils.cookies.CookiesHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.utils.driver.DriverManager.getDriver;

@Slf4j
public class TestBase {

    protected WebDriver driver;
    protected String baseURL = System.getProperty("baseURL");

    @Rule
    public TestName testName = new TestName();

    @BeforeClass
    public static void loadConfigProperties() {
        ConfigHandler.setConfigProperties();
    }

    @Before
    public void initTest() {
        log.info("Test name: {}", testName.getMethodName());
        driver = getDriver();
        driver.get(baseURL);
        log.info("Test URL: {}", baseURL);
        CookiesHandler.handleCookiesPopup(driver);
    }

    @After
    public void tearDown() {
        takeScreenshot(testName.getMethodName());
        if (BasePage.isInIframe) {
            switchToDefaultContent();
        }
        stopDriver();
    }

    private void stopDriver() {
        driver.close();
        driver.quit();
        log.info("Driver stopped");
    }

    private void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        BasePage.isInIframe = false;
        log.info("Switched to default content");
    }

    private void takeScreenshot(String testName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HH").format(new Date());
        String filePath = "screenshots/" + testName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(screenshot, new File(filePath));
            log.info("Screenshot saved: {}", filePath);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}