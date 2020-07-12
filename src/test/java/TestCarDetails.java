import com.carchecker.framework.CarDetails;
import com.carchecker.framework.CarRegistrationParser;
import com.carchecker.framework.carfactory.RetrieveFromFile;
import com.carchecker.framework.carfactory.RetrieveFromWebsite;
import com.carchecker.framework.carfactory.website.CarTaxCheckHomepage;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestCarDetails {
    public static Map<String, Optional<CarDetails>> actualCarDetailsMap;
    public static Map<String, Optional<CarDetails>> expectedCarDetailsMap;

    @BeforeClass
    public static void beforeClass() throws IOException {
        Path resourceDirectory = Paths.get("src", "main", "resources");
        String filePathInput = resourceDirectory.toFile().getAbsolutePath() + "\\data\\car_input.txt";
        String filePathOutput = resourceDirectory.toFile().getAbsolutePath() + "\\data\\car_output.txt";
        RetrieveFromWebsite retrieveActual = new RetrieveFromWebsite(new CarTaxCheckHomepage());
        actualCarDetailsMap = retrieveActual.getAllCarDetails(filePathInput);
        RetrieveFromFile retrieveExpected = new RetrieveFromFile();
        expectedCarDetailsMap = retrieveExpected.getAllCarDetails(filePathOutput);
    }

    @Test
    public void correctAmountExtracted() {
        assertThat(actualCarDetailsMap.size(), is(expectedCarDetailsMap.size()));
    }

    @Test
    public void testExtractedRegistrations() {
        assertThat(actualCarDetailsMap.keySet(), is(expectedCarDetailsMap.keySet()));
    }

    @Test
    public void testCarDetails() {
        assertThat(actualCarDetailsMap, is(expectedCarDetailsMap));
    }

}
