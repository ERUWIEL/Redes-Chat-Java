
package entidades;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * clase que representa un socket
 * @author erubiel
 */
public class Servidor implements Runnable{
    /**
     * Metodo que permite la concexion con un cliente
     */
    @Override
    public void run() {
        try{
            ServerSocket skServidor = new ServerSocket(1060);
            System.out.println("Corriendo Servidor TCP");
            while (true) {
                Socket skCliente = skServidor.accept();
                System.out.println("Cliente conectado: " + skCliente.getInetAddress());
                // crear un hilo dedicado a la gestion del usuario conectado
                new Thread(() -> gestorCliente(skCliente)).start();
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Metodo que permite gestionar la escucha a clientes
     * @param skCliente 
     */
    private void gestorCliente(Socket skCliente){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(skCliente.getInputStream()));
            PrintWriter out = new PrintWriter(skCliente.getOutputStream(), true); 
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + message);
                out.println("Servidor responde: " + message.toUpperCase());
            }
        } catch (IOException e) {
            System.err.println("Error con cliente: " + e.getMessage());
        } finally {
            try {
                skCliente.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar socket: " + e.getMessage());
            }
        }
    }
}
