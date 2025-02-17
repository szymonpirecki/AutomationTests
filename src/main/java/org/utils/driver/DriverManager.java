package org.utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
public class DriverManager {

    public static WebDriver getDriver() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        if (Boolean.parseBoolean(System.getProperty("headless"))) {
            options.addArguments("--headless=new");
            log.info("Tests are running in headless mode");
        }
        log.info("Driver started");
        return new ChromeDriver(options);
    }
}