package concurrence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;;

/**
 * @class This class present a Proxy implementation using sockets
 *
 * @author  Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 08.05.2018
 */
public class ThreadProxy extends Thread {
    
	private Socket sslSocketToClient; /** < The socket client */
    private final String ipServer;    /** < The server ip */
    private final int portServer;     /** < The server connection port */
    
    /**
     * The ThreadProxy parametrized constructor
     * @param sslSocketToClient
     * @param ipServer
     * @param portServer
     */
    public ThreadProxy(Socket sslSocketToClient, String ipServer, int portServer) {
        this.ipServer = ipServer;
        this.portServer = portServer;
        this.sslSocketToClient = sslSocketToClient;
        this.start();
    }
    
    @Override
    public void run() {
    	try (BufferedReader input = new BufferedReader(new InputStreamReader(sslSocketToClient.getInputStream()))) {
    		try {
    	    	// Read SSL key to connect via SSL to server
            	SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            	Socket sslSocketToServer = sslSocketFactory.createSocket(ipServer, portServer);
                BufferedReader inputClient = new BufferedReader(new InputStreamReader(sslSocketToServer.getInputStream()));
                PrintWriter outServer = new PrintWriter(sslSocketToServer.getOutputStream(), true);
                System.out.println("I received a message from client on port " +  sslSocketToClient.getPort() + " with ip " 
                				 + sslSocketToClient.getRemoteSocketAddress().toString()
                				 + " and i am going to send to server on port " + portServer + " with ip " + ipServer);
                outServer.println(input.readLine());
                // Receives the response from the server
                String answer = inputClient.readLine(); 
                // Send the server response to the client
                PrintWriter outClient = new PrintWriter(sslSocketToClient.getOutputStream(), true); 
                outClient.println(answer);
                sslSocketToServer.close();
                
            } finally {
            	sslSocketToClient.close();
            	System.out.println("Closed connection");
            }
        }
    	catch (IOException ex) {
            Logger.getLogger(ThreadProxy.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
    	