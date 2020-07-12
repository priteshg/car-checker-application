package com.carchecker.framework.carfactory.website;

import com.carchecker.framework.CarDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Optional;

public abstract class CarWebsite {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public CarWebsite() {
        //Note that this can be changed to for example chrome - left as firefox for simplicity
        SeleniumBrowserConfig config = new SeleniumBrowserConfig();
        this.driver = config.getDriver();
        this.wait = config.getWait();
    }

    public abstract Optional<CarDetails> getCarDetail(String carReg);


    public void quit() {
        driver.quit();
    }

}
