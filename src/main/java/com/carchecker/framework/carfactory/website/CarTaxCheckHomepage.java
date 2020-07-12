package com.carchecker.framework.carfactory.website;

import com.carchecker.framework.CarDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Optional;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CarTaxCheckHomepage extends CarWebsite {

    private static final String URL = "https://cartaxcheck.co.uk/";
    private static final By carRegInputElement = By.id("vrm-input");
    private static final By CareDetailsLoaded = By.tagName("dt");
    private static final String MAKE_FIELD = "Make";
    private static final String MODEL_FIELD = "Model";
    private static final String COLOUR_FIELD = "Colour";
    private static final String YEAR_FIELD = "Year";

    public String getFieldValue(String field) {
        return driver.findElement(By.xpath("//dt[text()='" + field + "']/following-sibling::dd")).getText();
    }

    private void navigateToCarDetails(String carReg) {
        driver.get(URL);
        driver.findElement(carRegInputElement).sendKeys(carReg + Keys.ENTER);
        wait.until(presenceOfElementLocated(CareDetailsLoaded));
    }

    @Override
    public Optional<CarDetails> getCarDetail(String carReg) {
        navigateToCarDetails(carReg);
        boolean carFound = !getFieldValue("Make").equals(""); //this determines whether the car has been found or not
        if (carFound) {
            return Optional.of(new CarDetails.Builder()
                    .make(getFieldValue(MAKE_FIELD))
                    .model(getFieldValue(MODEL_FIELD))
                    .colour(getFieldValue(COLOUR_FIELD))
                    .year(Integer.parseInt(getFieldValue(YEAR_FIELD)))
                    .build());
        } else {
            return Optional.empty();
        }
    }
}
