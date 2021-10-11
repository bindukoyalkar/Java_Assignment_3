import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PingIP {
    public static void runSystemCommand(String command) {
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String s = "";
            int index;
            double timePeriod=0.0;
            List<Double> listOfTimePeriods=new ArrayList<>();
            // reading output stream of the command
            while ((s = inputStream.readLine()) != null) {
                //System.out.println(s);
                if(s.contains("time=")){
                    index=s.indexOf("time=");
                    index=index+5;
                    int endIndex = s.indexOf("ms");
                    s = s.substring(index,endIndex);
                    timePeriod=Double.parseDouble(s);
                    listOfTimePeriods.add(timePeriod);
                }
            }
            Collections.sort(listOfTimePeriods);
            //System.out.println(listOfTimePeriods);
            System.out.println("Median time taken to ping the host is:"+listOfTimePeriods.get(2)+" ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String ip = sc.nextLine();
        runSystemCommand("ping -c 5 " + ip);
    }
}