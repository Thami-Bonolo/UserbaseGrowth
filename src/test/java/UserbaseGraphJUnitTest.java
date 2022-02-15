
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testConnection() throws MalformedURLException, IOException{
        ubg.conncet();
        int expected = 200;
        HttpURLConnection hr = ubg.getHR();
        int actual = hr.getResponseCode();
        assertEquals(expected, actual);

}
}
