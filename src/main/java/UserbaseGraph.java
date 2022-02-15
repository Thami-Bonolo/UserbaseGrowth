
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author bonolo
 */
public class UserbaseGraph {
    String start, end, line; 
    Object[][] array;
    URL url;
    HttpURLConnection  hr;
    BufferedReader bReader;

    public UserbaseGraph(String link,String... domain) throws MalformedURLException{
        setURL(link);
        if(domain.length > 0){
            setStart(domain[0]);
        }else{setStart("01 01 2022");}

        if(domain.length > 1){
            setEnd(domain[1]);
        }else{setEnd("15 01 2022");}
    }

    public final void setStart(String start) {
        this.start = start;
    }

    public final void setEnd(String end) {
        this.end = end;
    }

    private void setURL(String link) throws MalformedURLException{
        url = new URL(link);
    }

/*
 * Connecting to the url 
 */
    public void conncet(){
        try {
            hr = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
/*
 * Reading data from site
 */
    public void readData(){
        try {
            if(hr.getResponseCode() == 200){
                InputStream iStream = hr.getInputStream();

                bReader = new BufferedReader(
                    new InputStreamReader(iStream));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

/**
 * Domain of the Graph
     * @return 
     * @throws java.lang.Exception
 */
    public int domain()throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDateTime startDate = LocalDate.parse(start, dtf).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(end, dtf).atStartOfDay();
        long dateRange = Duration.between(startDate, endDate).toDays();
                    
        return (int) dateRange;
    }

/**
 * Store the data into an array
     * @throws java.lang.Exception
 */
    public void storeData() throws Exception{
        int lines = domain(), num = 0;
        array = new Object[lines+1][2];
        line = bReader.readLine();
        while(line.length()<3){
            line = bReader.readLine();
        }
        /*
         * Skip all other lines till the start date
         */
        while(!((line.trim().replace("\"", "").substring(0,2)).equals(start.substring(0, 2)))){
            line = bReader.readLine();
        }

        while(line!=null){
            if(line.contains(":")){
                String[] tempStrings = line.trim().split(":");
                array[num][0] = tempStrings[0].replace("\"", "");
                array[num][1] = Integer.parseInt(tempStrings[1].replace(",", ""));
                num++;
                /*
                 * Check if we reached the end date of our range
                 */
                if(((line.trim().replace("\"", "").substring(0,2)).equals(end.substring(0, 2)))){
                    break;
                }
            }
                    
            line = bReader.readLine();
        }
    }

    /**
    * write to File
    * @param filename
     * @throws java.io.IOException
    */
    public void writeToFile(String filename) throws IOException{
        BufferedWriter bWriter = new BufferedWriter(
                new FileWriter(new File(filename)));

        for(int i=0; i<array.length; i++){
            if(array[i][1] != null){
                bWriter.write(array[i][0]+ " " +array[i][1]);
                bWriter.newLine();
            }
        }
        bWriter.close();
    }
    /**
    * These methods are only added for testing purposes
     * @param filename
    */
    public void deleteFile(String filename){
        File file = new File(filename);
        if(file.delete()){
            System.out.println("File succesfully deleted");
        }
    }

    public HttpURLConnection getHR(){
        return hr;
    }

    public BufferedReader getReader(){
        return bReader;
    }

public Object[][] getArray(){
return array;
}

    public void RunPython(){

        try {
            String command = "python3 Graph.py";
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line2;

            while ((line2 = bri.readLine()) != null) {
                System.out.println(line2);
            }
            bri.close();

            while ((line2 = bre.readLine()) != null) {
                System.out.println(line2);
            }
            bre.close();
            p.waitFor();

            p.destroy();
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
        
    }

}
