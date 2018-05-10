import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


class ThreadServer extends Thread {
    private Socket sClient;
    
    ThreadServer(Socket sClient) {
        this.sClient = sClient;
        this.start();
    }
    @Override
    public void run() {
    	try {
    		System.out.println("ServerSocket accepted");
            System.out.println(sClient.getRemoteSocketAddress());
             
            PrintWriter out = new PrintWriter(sClient.getOutputStream(), true);
            try (BufferedReader bufferedReader = 
                    new BufferedReader(
                            new InputStreamReader(sClient.getInputStream()))) {
                String line;
                Scanner scanner = new Scanner(System.in);
                while((line = bufferedReader.readLine()) != null){
                    System.out.println(line);                 
                    System.out.println("Enter something:");
                    String inputLine = scanner.nextLine();
                    out.println(inputLine);
                }
            }
            System.out.println("Closed");
    	}
    	catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}