import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PingIP {
    public static void runSystemCommand(String command) {
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String s = "";
            int index,count=0;
            double timePeriod=0.0;
            // reading output stream of the command
            while ((s = inputStream.readLine()) != null) {
                if(s.contains("time=")){
                    index=s.indexOf("time=");
                    index=index+5;
                    //System.out.println(s);
                    s=s.substring(index,index+5);
                    //System.out.println(s);
                    timePeriod=Double.parseDouble(s);
                    count++;
                }
                if(count==2)
                    System.out.println("Median time taken to ping the host is:"+timePeriod);

            }

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