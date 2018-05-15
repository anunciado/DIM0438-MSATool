package network;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;

import concurrence.ThreadServer;

/**
 * This class present a implementation of a Server using multiple threads.
 * 
 * @author  Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 10.05.2018
 */
public class ServerMultiThread {
     
    //static final int portProxy = 9011;
    //static final int backlog = 10;
 
    public static void main(String[] args) {
    	int portProxy = Integer.parseInt(args[0]);
    	int backlog =  Integer.parseInt(args[1]);
    	System.setProperty("javax.net.ssl.keyStore","/home/luiseduardo/eclipse-workspace/MSA/src/examplestore");
    	System.setProperty("javax.net.ssl.keyStorePassword", "123456");
    	// Read SSL key to connect via SSL to proxy
        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();        
        try {
            ServerSocket sslSocketToProxy = sslServerSocketFactory.createServerSocket(portProxy, backlog);
            System.out.println("SSL ServerSocket started" + "\nPort: " + portProxy);
            while (true) {
            	System.out.println("Number of clients at this moment on the server: " + (Thread.activeCount() - 1));
                new ThreadServer(sslSocketToProxy.accept(), Thread.activeCount() - 1);
            }             
        } catch (IOException ex) {
            Logger.getLogger(ServerMultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
}