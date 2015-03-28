import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        try {
            ServerSocket socket = new ServerSocket(port);
            while(true){
                System.out.println("\nServidor Listo, esperando llegada de cliente");
                Socket clientSocket = socket.accept();
                System.out.println("Ha llegado cliente: " + clientSocket.getInetAddress());
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));
                String input;
                while  ((input = in.readLine()) != null){
                    if (input.contains("complete")){
                        break;
                    }
                    System.out.println(input);
                }
                System.out.println("\nEnviando Informacion");

                out.println("Sistema Operativo " + System.getProperty("os.name"));
                out.println(Runtime.getRuntime().availableProcessors() + " Procesadores disponibles");
                out.println(Runtime.getRuntime().freeMemory() + " bytes disponibles en memoria");
                out.println("Inet4Address: " + Inet4Address.getLocalHost().getHostAddress());
                out.println("complete");

            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}