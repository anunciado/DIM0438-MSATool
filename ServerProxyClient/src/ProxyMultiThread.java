import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;;

public class ProxyMultiThread{
	
	static final int portServer = 9010;
	static final int portClient = 9000;
    static final String url = "127.0.0.1";
    
	public static void main(String[] args) {
	    
		SSLServerSocketFactory sslServerSocketFactory = 
                (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
         
        try {
            ServerSocket sslServerSocket = 
                    sslServerSocketFactory.createServerSocket(portClient, 1);
            System.out.println("SSL ProxySocket started");
            System.out.println(sslServerSocket.toString());
            while (true) {
            	System.out.println("Números de clientes no momento: " + Thread.activeCount());
                new ThreadProxy(sslServerSocket.accept(), url, portServer);
            }
             
        } catch (IOException ex) {
            Logger.getLogger(ProxyMultiThread.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
	}
}
