import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;

public class ServerMultiThread {
     
    static final int portProxy = 9010;
 
    public static void main(String[] args) {
              
        SSLServerSocketFactory sslServerSocketFactory = 
                (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
         
        try {
            ServerSocket sslServerSocket = 
                    sslServerSocketFactory.createServerSocket(portProxy);
            System.out.println("SSL ServerSocket started");
            System.out.println(sslServerSocket.toString());
            while (true) {
            	System.out.println("Números de clientes no momento: " + Thread.activeCount());
                new ThreadServer(sslServerSocket.accept());
            }
             
        } catch (IOException ex) {
            Logger.getLogger(ServerMultiThread.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
     
}