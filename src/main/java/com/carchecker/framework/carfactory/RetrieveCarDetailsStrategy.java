package com.carchecker.framework.carfactory;

import com.carchecker.framework.CarDetails;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface RetrieveCarDetailsStrategy {
    Map<String, Optional<CarDetails>> getAllCarDetails(String filePath) throws IOException;
}
