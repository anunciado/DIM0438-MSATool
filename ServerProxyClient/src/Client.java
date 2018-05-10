import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;
 
public class Client {
     
    static final int portProxy = 9000;
    static final String url = "127.0.0.1";
 
    public static void main(String[] args) {
         
        SSLSocketFactory sslSocketFactory = 
                (SSLSocketFactory)SSLSocketFactory.getDefault();
        try {
            Socket socket = sslSocketFactory.createSocket(url, portProxy);
            System.out.println("SSL ClientSocket started");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try (BufferedReader bufferedReader = 
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                Scanner scanner = new Scanner(System.in);
                while(true){
                    System.out.println("Enter something:");
                    String inputLine = scanner.nextLine();
                    if(inputLine.equals("q")){
                        break;
                    }
                     
                    out.println(inputLine);
                    System.out.println(bufferedReader.readLine());
                }
            }
             
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
          
    }
     
}