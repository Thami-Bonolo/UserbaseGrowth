
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
        String link = "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/";
        String inputStart, inputEnd;
        if(args.length >0){

            //Checking if dates are valid
            if(args[0].length() != 10){
                System.out.println("Start date is incorrect. use format \"dd MM yy\".");
                return;
            }else if(args.length>1 && args[1].length() != 10){
                System.out.println("End date is incorrect. use format \"dd MM yy\".");
                return;
            }
            if(args[0] != null){
                inputStart = args[0];
            }else{
                inputStart = "01 01 2022";
            }
            if(args.length>=2 && args[1] != null){
                inputEnd = args[1];
            }else{
                inputEnd = "15 01 2022";
            }
        }else{
            inputStart = "01 01 2022";
            inputEnd = "15 01 2022";
        }
        UserbaseGraph ubg = new UserbaseGraph(link, inputStart, inputEnd);
        ubg.conncet();  //Connect to the URL
        ubg.readData(); //create a bufferedreader of site data
        ubg.storeData();    //store data in an array of object for easy processing
        ubg.writeToFile("GraphData.txt");   //write data to file to be accessed by python
        ubg.RunPython();    //Draw the graph on terminal using plotext in python3
        ubg.deleteFile("GraphData.txt");    //delete the data file.
    }
    
}
