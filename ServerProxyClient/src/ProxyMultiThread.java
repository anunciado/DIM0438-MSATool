import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;;

public class ProxyMultiThread{
	
	static final int portServer1 = 9010;
	static final int portServer2 = 9020;
	static final int portClient = 9000;
    static final String ipServer1 = "127.0.0.1";
    static final String ipServer2 = "127.0.0.1";
    
	public static void main(String[] args) {
		// Read SSL key to connect via SSL to client and servers
	    SSLServerSocketFactory sslProxySocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();       
        try {
        	// Create server socket to clients
            ServerSocket sslSocketToClient =  sslProxySocketFactory.createServerSocket(portClient);
            System.out.println("SSL ProxySocket started" + "\nPort to client: " + portClient 
            				 + "\nPort to server 1: " + portServer1 + "\nPort to server 2: " + portServer2 
            				 + "\nServer 1 IP: " + ipServer1 + "\nServer 2 IP: " + ipServer2);
            boolean chooseServer = true; 
        	// Create sockets to servers depending on the requisitions of the clients
            while (true) {
            	System.out.println("Number of clients at this moment on the proxy: " + (Thread.activeCount() - 1));
            	if (chooseServer) {
            		new ThreadProxy(sslSocketToClient.accept(), ipServer1, portServer1);
            		//chooseServer = false; 
            	}
            	else {
            		new ThreadProxy(sslSocketToClient.accept(), ipServer2, portServer2);
            		//chooseServer = true; 
            	}
            }
             
        } catch (IOException ex) {
            Logger.getLogger(ProxyMultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
