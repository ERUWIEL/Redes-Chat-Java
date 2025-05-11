package entidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * clase que representa un socket
 *
 * @author erubiel
 */
public class Servidor {

    private ServerSocket skServidorTCP;
    //private DatagramSocket skServidorUDP;
    private InetAddress ipServidor;
    //private Cliente admin;
    private List<Socket> skClientes = new ArrayList<>(6);
    private List<Cliente> clientes = new ArrayList<>(6);
    

    /**
     * Inicializa un objeto Servidor con un puerto mandado
     *
     * @param puerto
     */
    public Servidor(int puerto) {
        try {
            this.skServidorTCP = new ServerSocket(puerto);
            this.ipServidor = skServidorTCP.getInetAddress();
            new Thread(() -> conectarClienteTCP()).start();
            //new Thread(() -> conectarClienteUDP()).start();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR EN EL PUERTO", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /**
     * Metodo que permite la concexion TCP con un cliente para enviar y recibir
     * mensajes
     */
    private void conectarClienteTCP() {
        
        try {
            System.out.println("Corriendo Servidor TCP");
            Cliente cliente = new Cliente("localhost", "contraseña");
            while (true) {
                
                cliente.asignarServidor(ipServidor.getHostAddress(), skServidorTCP.getLocalPort());
                clientes.add(cliente);
                Socket skCliente = skServidorTCP.accept();
                System.out.println("Cliente conectado: " + skCliente.getInetAddress());
                skClientes.add(skCliente);
                
                // crear un hilo dedicado a la gestion del usuario conectado
                new Thread(() -> gestorCliente(skCliente)).start();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR AL CLIENTE", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Metodo que permite gestionar la escucha a clientes
     *
     * @param skCliente
     */
    private void gestorCliente(Socket skCliente) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(skCliente.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) {
                // logica de mandar el mensje a los demas clientes
                for(Socket x : skClientes){
                    PrintWriter out = new PrintWriter(x.getOutputStream(), true);
                    out.println(message.toUpperCase());
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL GESTIONAR CLIENTE", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                skCliente.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar socket: " + e.getMessage());
            }
        }
    }


    /**  Solo comente lo que un no se ha implementado
    private void conectarClienteUDP() {
        try {
            skServidorUDP = new DatagramSocket();
            byte[] buffer = new byte[1024];

            System.out.println("Servidor UDP esperando...");

            while (true) {
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                skServidorUDP.receive(paqueteRecibido);
                DatagramPacket paqueteRespuesta = new DatagramPacket(
                        paqueteRecibido.getData(), paqueteRecibido.getData().length,
                        paqueteRecibido.getAddress(), paqueteRecibido.getPort());
                skServidorUDP.send(paqueteRespuesta);
            }
        } catch (IOException ex) {
        }
    }
    */
}
