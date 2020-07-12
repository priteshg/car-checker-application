package com.carchecker.framework.carfactory;

import com.carchecker.framework.CarDetails;
import com.carchecker.framework.CarRegistrationParser;
import com.carchecker.framework.carfactory.website.CarWebsite;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This uses the CarRegistrationParser to parse the input file for car registrations and then extract details from the car website
 * and outputs a HashMap  Map<String, Optional<CarDetails>>
 */
public class RetrieveFromWebsite implements RetrieveCarDetailsStrategy {
    private final CarWebsite carWebsite;


    public RetrieveFromWebsite(CarWebsite carWebsite) {
        this.carWebsite = carWebsite;
    }

    public Map<String, Optional<CarDetails>> getAllCarDetails(String filePath) throws IOException {
        CarRegistrationParser carRegistrationParser = new CarRegistrationParser(filePath);
        List<String> regNumbers = carRegistrationParser.getCarRegistrations();
        Map<String, Optional<CarDetails>> carDetailsMap = new HashMap<>();

        for (String reg : regNumbers) {
            carDetailsMap.put(reg, carWebsite.getCarDetail(reg));
        }
        carWebsite.quit();
        return carDetailsMap;
    }


}
