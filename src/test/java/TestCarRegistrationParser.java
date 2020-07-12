import com.carchecker.framework.CarRegistrationParser;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCarRegistrationParser {

    private static final String TEMP_FILE_NAME = "temp.txt";
    private static final Path TEMP_FILE_PATH = Paths.get(TEMP_FILE_NAME);

    @Test
    public void noSpacesInRegistration() throws IOException {
        List<String> expected = Arrays.asList("DN09HRM");
        String input = "Checking example BMW with registration DN09HRM the value of the car is roughly around £3000.";
        testRegistrationList(expected, input);
    }

    @Test
    public void spacesInRegistration() throws IOException {
        List<String> expected = Arrays.asList("BW57BOW");
        String input = "However car with registration BW57 BOW is not worth much in current market. ";
        testRegistrationList(expected, input);
    }

    @Test
    public void containingMultipleRegistrations() throws IOException {
        List<String> expected = Arrays.asList("KT17DLX", "SG18HTN");
        String input = "There are multiple cars available higher than £10k with registraions KT17DLX and SG18 HTN.";
        testRegistrationList(expected, input);
    }

    @Test
    public void containingMultipleLines() throws IOException {
        List<String> expected = Arrays.asList("KT17DLX", "SG18HTN", "BW57BOW");
        String input = "There are multiple cars available higher than £10k with registraions KT17DLX and SG18 HTN. \n"
                + "However car with registration BW57 BOW is not worth much in current market. ";
        testRegistrationList(expected, input);
    }

    private void testRegistrationList(List<String> expected, String input) throws IOException {
        CarRegistrationParser carRegistrationParser = new CarRegistrationParser(createFileWithText(input));
        assertThat(carRegistrationParser.getCarRegistrations(), is(expected));
    }

    private String createFileWithText(String line) throws IOException {
        Files.createFile(TEMP_FILE_PATH);
        Files.write(TEMP_FILE_PATH, line.getBytes());
        return TEMP_FILE_PATH.toString();
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(TEMP_FILE_PATH);
    }
}
