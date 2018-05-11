import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;

public class ServerMultiThread {
     
    static final int portProxy = 9010;
    static final int backlog = 10;
 
    public static void main(String[] args) {
    	// Read SSL key to connect via SSL to proxy
        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();        
        try {
            ServerSocket sslSocketToProxy = sslServerSocketFactory.createServerSocket(portProxy, backlog);
            System.out.println("SSL ServerSocket started" + "\nPort: " + portProxy);
            while (true) {
            	System.out.println("Number of clients at this moment on the server: " + (Thread.activeCount() - 1));
                new ThreadServer(sslSocketToProxy.accept());
            }             
        } catch (IOException ex) {
            Logger.getLogger(ServerMultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
}