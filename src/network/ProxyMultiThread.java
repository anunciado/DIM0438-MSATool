package network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

import concurrence.ThreadProxy;

/**
 * @class   This class present a implementation of a Proxy using mutiple threads.
 *
 * @author  Luis Eduardo (cruxiu@ufrn.edu.br)
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 10.05.2018
 */
public class ProxyMultiThread{
	
	private static String ipServer; /** < Address to connect with the best server available */ 
	private static int portServer;  /** < Port to connect with the best server available */ 
	private static int portServer1; /** < Port to connect with the server 1 */ 
	private static int portServer2; /** < Port to connect with the server 2 */ 
	private static int portClient; /** < Port client connects with proxy */
    private static String ipServer1; /** < Address to connect with the server 1 */ 
    private static String ipServer2; /** < Address to connect with the server 2 */ 
    
	public static void main(String[] args) {
		portServer1 = Integer.parseInt(args[0]);
		portServer2 = Integer.parseInt(args[1]);
		portClient = Integer.parseInt(args[2]);
	    ipServer1 = args[3];
	    ipServer2 = args[4];
	    ipServer = ipServer1;
	    portServer = portServer1;
	    System.setProperty("javax.net.ssl.keyStore","/home/luiseduardo/eclipse-workspace/MSA/src/examplestore");
    	System.setProperty("javax.net.ssl.keyStorePassword","123456");
    	System.setProperty("javax.net.ssl.trustStore","/home/luiseduardo/eclipse-workspace/MSA/src/examplestore");
    	System.setProperty("javax.net.ssl.trustStorePassword","123456");
		// Read SSL key to connect via SSL to client and servers
	    SSLServerSocketFactory sslProxySocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();       
        try {
        	// Create server socket to clients
            ServerSocket sslSocketToClient =  sslProxySocketFactory.createServerSocket(portClient);
            System.out.println("SSL ProxySocket started" + "\nPort to client: " + portClient 
            				 + "\nPort to server 1: " + portServer1 + "\nPort to server 2: " + portServer2 
            				 + "\nServer 1 IP: " + ipServer1 + "\nServer 2 IP: " + ipServer2);
        	// Create sockets to servers depending on the requisitions of the clients
            while (true) {
            	System.out.println("Number of clients at this moment on the proxy: " + (Thread.activeCount() - 1));
            	Socket s = sslSocketToClient.accept();
            	chooseBestServer();
            	new ThreadProxy(s, ipServer, portServer);
            }
             
        } catch (IOException ex) {
            Logger.getLogger(ProxyMultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	private static void chooseBestServer() {
		// Read SSL key to connect via SSL to proxy
        SSLSocketFactory sslSocketFactoryServer1 = (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocketFactory sslSocketFactoryServer2 = (SSLSocketFactory)SSLSocketFactory.getDefault();
        try {
        	// Create socket to proxy
            Socket sslSocketToServer1 = sslSocketFactoryServer1.createSocket(ipServer1, portServer1);
            Socket sslSocketToServer2 = sslSocketFactoryServer2.createSocket(ipServer2, portServer2);
            // Create output stream object to send messages to proxy
            PrintWriter out1 = new PrintWriter(sslSocketToServer1.getOutputStream(), true);
            int in1;
            PrintWriter out2 = new PrintWriter(sslSocketToServer2.getOutputStream(), true);
            int in2;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocketToServer1.getInputStream()))) {
            	out1.println("How many");
                in1 = Integer.parseInt(bufferedReader.readLine());
                sslSocketToServer1.close();
            }
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocketToServer2.getInputStream()))) {
            	out2.println("How many");
                in2 = Integer.parseInt(bufferedReader.readLine());
                sslSocketToServer2.close();
            }
            if (in1 > in2) { 
            	ipServer = ipServer2;
        	    portServer = portServer2;
            } 
            else {
            	ipServer = ipServer1;
        	    portServer = portServer1;
            }
        } catch (IOException ex) {
        	Logger.getLogger(ProxyMultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }   		
	}
}
