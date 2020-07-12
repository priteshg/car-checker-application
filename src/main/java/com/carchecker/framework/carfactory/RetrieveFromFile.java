package com.carchecker.framework.carfactory;

import com.carchecker.framework.CarDetails;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This extracts registrations and associated car details from the output_file.txt and outputs a HashMap  Map<String, Optional<CarDetails>>
 */
public class RetrieveFromFile {

    public Map<String, Optional<CarDetails>> getAllCarDetails(String fileName) {
        Map<String, Optional<CarDetails>> carDetailsMap = new HashMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName)).skip(1)) {
            stream.forEach(line -> {
                String[] fields = line.split(",");
                carDetailsMap.put(fields[0],
                        Optional.of(new CarDetails.Builder()
                                .make(fields[1])
                                .model(fields[2])
                                .colour(fields[3])
                                .year(fields[4]).build()));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carDetailsMap;
    }
}
