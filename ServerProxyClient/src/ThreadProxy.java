import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;;

class ThreadProxy extends Thread {
    private Socket sClient;
    private final String SERVER_URL;
    private final int SERVER_PORT;
    ThreadProxy(Socket sClient, String ServerUrl, int ServerPort) {
        this.SERVER_URL = ServerUrl;
        this.SERVER_PORT = ServerPort;
        this.sClient = sClient;
        this.start();
    }
    @Override
    public void run() {
    	try (BufferedReader input = new BufferedReader(new InputStreamReader(sClient.getInputStream()))) {
    		try {
            	SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            	Socket s = sslSocketFactory.createSocket(SERVER_URL, SERVER_PORT);
                BufferedReader inputClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                System.out.println("Recebi a menssagem e estou repassando para o server...");
                out.println(input.readLine());
                String answer = inputClient.readLine(); //recebe a resposta do server
                
                PrintWriter out2 = new PrintWriter(sClient.getOutputStream(), true); // manda a resposta do server para p o cliente
                out2.println(answer);
                s.close();
                
            } finally {
            	sClient.close();
            }
        }
    	catch (IOException ex) {
            Logger.getLogger(ThreadProxy.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
    	