
import java.net.MalformedURLException;

/**
 *
 * @author bonolo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, Exception {
        UserbaseGraph ubg = new UserbaseGraph("http://sam-user-activity.eu-west-1.elasticbeanstalk.com/");
        ubg.conncet();
        ubg.readData();
        ubg.storeData();
        ubg.writeToFile("GraphData.txt");
        ubg.RunPython();
        ubg.deleteFile("GraphData.txt");
    }
    
}
