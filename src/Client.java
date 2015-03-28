import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            Socket socket = new Socket(hostname, port);
            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn =
                    new BufferedReader(
                        new InputStreamReader(System.in));

            System.out.println("Conexion Establecida -- Enviando Informacion");

            out.println("Sistema Operativo " + System.getProperty("os.name"));
            out.println(Runtime.getRuntime().availableProcessors() + " Procesadores disponibles");
            out.println(Runtime.getRuntime().freeMemory() + " bytes disponibles en memoria");
            out.println("Inet4Address: " + Inet4Address.getLocalHost().getHostAddress());
            out.println("complete");

            System.out.println("Recibiendo informacion\n");

            String input;
            while  ((input = in.readLine()) != null){
                if (input.contains("complete")){
                    break;
                }
                System.out.println(input);
            }
            System.out.println();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
