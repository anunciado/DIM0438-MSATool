import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @class This class present a server implementation using sockets
 *
 * @author  Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 08.05.2018
 */
public class ThreadServer extends Thread {
    private Socket sslSocketToProxy; /** The proxy socket */
    
    /** 
     * The parametrized constructor
     * @param sslSockectToProxy
     */
    public ThreadServer(Socket sslSocketToProxy) {
        this.sslSocketToProxy = sslSocketToProxy;
        this.start();
    }
	
    @Override
    public void run() {
    	try {
    		System.out.println("Accepted request from proxy on port " +  sslSocketToProxy.getPort() + " with ip "  
    						+ sslSocketToProxy.getRemoteSocketAddress().toString());
            System.out.println(sslSocketToProxy.getRemoteSocketAddress());            
            PrintWriter outProxy = new PrintWriter(sslSocketToProxy.getOutputStream(), true);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocketToProxy.getInputStream()))) {
                // Receive message of the proxy
                String message = bufferedReader.readLine();
                System.out.println("Message received from the proxy:");
                System.out.println(message);                 
                System.out.println("Enter a answer to proxy:");
                // Send the server response to the proxy
                MSA msa = new MSA(new ArrayList<>(Arrays.asList(message.replaceAll("\\s+","").split(","))));  
                try {
					outProxy.println(msa.multipleSequenceAlignment().replaceAll("\n","\t"));
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
            System.out.println("Closed connection");
    	}
    	catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
