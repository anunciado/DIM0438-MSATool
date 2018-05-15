import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;

/**
 * @class The Client class implements a client structure by sockets. 
 *
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author  Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @version 14.05.2018
 */
public class Client {
     
    static final int portProxy = 9001;
    static final String ipProxy = "127.0.0.1";
    
    public static void main(String[] args) {
    	// Read SSL key to connect via SSL to proxy
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        try {
        	// Create socket to proxy
            Socket sslSocketToProxy = sslSocketFactory.createSocket(ipProxy, portProxy);
            System.out.println("SSL ClientSocket started" + "\nPort: " + portProxy + "\nProxy IP: " + ipProxy);
            // Create output stream object to send messages to proxy
            PrintWriter out = new PrintWriter(sslSocketToProxy.getOutputStream(), true);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocketToProxy.getInputStream()))) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter a message to proxy:");
                String inputLine = scanner.nextLine();  
                // Send a message to proxy
                out.println(inputLine);
                // Receive and print a message from the proxy
                System.out.println("Message received from the proxy:");
                System.out.println(bufferedReader.readLine().replaceAll("\t","\n"));
                System.out.println("Closed connection");
            }           
        } catch (IOException ex) {
        	Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}
