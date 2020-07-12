package com.carchecker.framework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarRegistrationParser {
    public static final String REGISTRATION_FINDER_REGEX = "([A-Z][A-Z]\\d\\d\\s?[A-Z][A-Z][A-Z])";
    private final List<String> carRegistrations;


    public CarRegistrationParser(String filePath) throws IOException {
        //For each line get the car registrations
        Stream<String> stream = Files.lines(Paths.get(filePath));
        carRegistrations = stream.map(this::getMatchedCarRegistrations).
                flatMap(List::stream).//flatMap to get Stream<List<String>>
                map(reg -> reg.replaceAll("\\s+", "")).
                collect(Collectors.toList());
    }


    public List<String> getCarRegistrations() {
        return carRegistrations;
    }

    public List<String> getMatchedCarRegistrations(String line) {
        return Pattern.compile(REGISTRATION_FINDER_REGEX)
                .matcher(line)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());

    }


}
