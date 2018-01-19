import java.util.Scanner;
import java.util.regex.Pattern;
import java.net.Socket;
import java.io.IOException;
import java.lang.*;

public class PortScanner {
    
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String initialIp;
        String finalIp;
        String initialPort;
        String finalPort;
        while (true) {
            System.out.println("Enter the initial IP");
            initialIp = userInput.next();
            if (ValidateIPV4.isValidIp(initialIp)) {
                break;
            }
        }
        while (true) {
            System.out.println("Enter the final IP");
            finalIp = userInput.next();
            if (ValidateIPV4.isValidIp(finalIp)) {
                break;
            }
        }
        while (true) {
            System.out.println("Enter the initial port");
            initialPort = userInput.next();
            if (ValidateIPV4.isValidPort(initialPort)) {
                break;
            }
        }
        while (true) {
            System.out.println("Enter the final port");
            finalPort = userInput.next();
            if (ValidateIPV4.isValidPort(finalPort)) {
                break;
            }
        }
        System.out.println("Please wait while scanning...");
        String[] initialIpSplitted = initialIp.split("\\.");
        String[] finalIpSplitted = finalIp.split("\\.");
        int incrementalNum0 = Integer.parseInt(initialIpSplitted[0]);
        int incrementalNum1 = Integer.parseInt(initialIpSplitted[1]);
        int incrementalNum2 = Integer.parseInt(initialIpSplitted[2]);
        int incrementalNum3 = Integer.parseInt(initialIpSplitted[3]);	
    	long timeStart = System.currentTimeMillis();
        for (int i = incrementalNum0; i <= Integer.parseInt(finalIpSplitted[0]);i++) {
            for (int j = incrementalNum1; j <= Integer.parseInt(finalIpSplitted[1]);j++) {
                for (int k = incrementalNum2; k <= Integer.parseInt(finalIpSplitted[2]);k++) {
                    for (int l = incrementalNum3; l <= Integer.parseInt(finalIpSplitted[3]);l++) {
                        for (int m = Integer.parseInt(initialPort); m <= Integer.parseInt(finalPort); m++) {
                            try {
                                Socket socket = new Socket(i+"."+j+"."+k+"."+l, m);
                                socket.close();
                                String text = "IP: "+ i+"."+j+"."+k+"."+l+" Port OPEN: "+m;
                                System.out.println(text);
                           }
                           catch (IOException e) {
                           }
                        }
                    }
                }
            }
        }
        long timeEnd = System.currentTimeMillis();
        long passedTime = (timeEnd - timeStart)/1000;
        System.out.println("Scanning Completed in: "+passedTime+ " seconds");
    }
}

class ValidateIPV4 {
   static private final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
   static private final String PORT_REGEX = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
   static private Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
   static private Pattern PORT_PATTERN = Pattern.compile(PORT_REGEX);

   public static boolean isValidIp(final String s) {          
      return IPV4_PATTERN.matcher(s).matches();
   }

   public static boolean isValidPort(final String s) {
       return PORT_PATTERN.matcher(s).matches();
   }
}