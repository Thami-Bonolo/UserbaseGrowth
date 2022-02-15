
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author bonolo
 */
public class UserbaseGraphJUnitTest {
    UserbaseGraph ubg;
    public UserbaseGraphJUnitTest() {
    }
    
    @BeforeAll
    public static void setUpClass() throws MalformedURLException {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws MalformedURLException {
        ubg = new UserbaseGraph("http://sam-user-activity.eu-west-1.elasticbeanstalk.com/");
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testConnection() throws MalformedURLException, IOException{
        ubg.conncet();
        HttpURLConnection hr = ubg.getHR();
        int expected = 200;
        int actual = hr.getResponseCode();
        assertEquals(expected, actual);
}
    @Test
    public void testReadData() throws IOException{
        ubg.conncet();
        ubg.readData();
        BufferedReader line = ubg.getReader();
        line.readLine();
        String expected = "    \"01-01-2022\":300,     \"02-01-2022\":500,     \"03-01-2022\":700,";
        String actual = line.readLine() + " " + line.readLine() + " " + line.readLine();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Calculation of range from start and end date should work")
    public void testDomain() throws Exception{
        ubg.setStart("02 01 2022");
        ubg.setEnd("08 01 2022");
        int expected = 6;
        int actual = ubg.domain();
        assertEquals(expected, actual, "range calculation should work");
    }

    @Test
    public void storeDataTest() throws Exception{
        ubg.setStart("01 01 2022");
        ubg.setEnd("03 01 2022");
        ubg.conncet();
        ubg.readData();
        Object[][] expected = {{"01-01-2022", 300}, {"02-01-2022", 500}, {"03-01-2022", 700}};
        ubg.storeData();
        Object[][] actual = ubg.getArray();
        assertTrue(Arrays.equals(expected[0], actual[0]));
        assertTrue(Arrays.equals(expected[1], actual[1]));
        assertTrue(Arrays.equals(expected[2], actual[2]));
    }

    @Test
    public void writeToFileTest() throws IOException, Exception{
        ubg.setStart("01 01 2022");
        ubg.setEnd("03 01 2022");
        ubg.conncet();
        ubg.readData();
        ubg.storeData();
        ubg.writeToFile("tempFile.txt");
        File temp = new File("tempFile.txt");

        //Test if file was created
        assertTrue(temp.exists());

        String expected = "01-01-2022 300\n" +
            "02-01-2022 500\n" + "03-01-2022 700\n";

        String actual = new String(Files.readAllBytes(Paths.get("tempFile.txt")),
            StandardCharsets.UTF_8);
        // Testing contents of the file
        assertEquals(expected, actual);

        ubg.deleteFile("tempFile.txt");
    }
}
