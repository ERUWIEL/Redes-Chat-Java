package entidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author angel
 */
public class Cliente implements Runnable {

    @Override
    public void run() {
        try {
            //se crea el socket de usuario
            Socket socket = new Socket("localhost", 1060);
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Conectado al servidor. Escribe mensajes:");
            
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Respuesta: " + in.readLine());
            }
        } catch (IOException e) {
            System.err.println("Error en cliente: " + e.getMessage());
        }
    }

}
