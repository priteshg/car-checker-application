package com.carchecker.framework.carfactory.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumBrowserConfig {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public static final String FIREFOX_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/geckodriver.exe";

    public SeleniumBrowserConfig() {
        System.setProperty("webdriver.gecko.driver", SeleniumBrowserConfig.FIREFOX_DRIVER_PATH);
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 100);
    }


    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

}
