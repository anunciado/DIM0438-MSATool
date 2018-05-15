package network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;

import gui.Index;

/**
 * @class The Client class implements a client structure by sockets. 
 *
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author  Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @version 14.05.2018
 */
public class Client {
     
    //static final int portProxy = 9002;
    //static final String ipProxy = "127.0.0.1";
    
    public static void main(String[] args) throws InterruptedException {
    	int portProxy = Integer.parseInt(args[0]);
        String ipProxy = args[1];
        System.setProperty("javax.net.ssl.trustStore","/home/luiseduardo/eclipse-workspace/MSA/src/examplestore");
    	System.setProperty("javax.net.ssl.trustStorePassword","123456");
		Index index = new Index();
    	// Read SSL key to connect via SSL to proxy
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        try {
        	// Create socket to proxy
            Socket sslSocketToProxy = sslSocketFactory.createSocket(ipProxy, portProxy);
            System.out.println("SSL ClientSocket started" + "\nPort: " + portProxy + "\nProxy IP: " + ipProxy);
            // Create output stream object to send messages to proxy
            PrintWriter out = new PrintWriter(sslSocketToProxy.getOutputStream(), true);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocketToProxy.getInputStream()))) {
            	// Wait for user enter the message in the gui
            	while(index.getProteinSequence() == null) { Thread.sleep(1000);}
            	out.println(index.getProteinSequence());
                
                // Receive and print a message from the proxy
                System.out.println("Message received from the proxy:");
//                System.out.println(bufferedReader.readLine().replaceAll("\t","\n"));
                String align = bufferedReader.readLine().replaceAll("\t","\n");
                System.out.println(align);
                index.setProteinAling(align);
           
                System.out.println("Closed connection");
                sslSocketToProxy.close();
            }           
        } catch (IOException ex) {
        	Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}