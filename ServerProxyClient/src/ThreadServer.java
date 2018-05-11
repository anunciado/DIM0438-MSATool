import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


class ThreadServer extends Thread {
    private Socket sslSocketToProxy;
    
    ThreadServer(Socket sslSocketToProxy) {
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
                Scanner scanner = new Scanner(System.in);
                // Receive message of the proxy
                String message = bufferedReader.readLine();
                System.out.println("Message received from the proxy:");
                System.out.println(message);                 
                System.out.println("Enter a answer to proxy:");
                // Send the server response to the proxy
                String answer = scanner.nextLine();
                outProxy.println(answer);
            }
            System.out.println("Closed connection");
    	}
    	catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}