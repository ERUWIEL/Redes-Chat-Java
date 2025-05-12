package entidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * clase que representa un socket
 *
 * @author erubiel
 */
public class Servidor {

    private ServerSocket skServidorTCP;
    private DatagramSocket skServidorUDP;
    private InetAddress ipServidor;

    private List<Socket> skClientes = new ArrayList<>(6);
    private List<Cliente> clientes = new ArrayList<>(6);

    /**
     * Inicializa un objeto Servidor con un puerto mandado
     *
     * @param ipServidor
     * @param puerto
     */
    public Servidor(int puerto) {
        try {
            
            this.skServidorTCP = new ServerSocket(puerto, 5, InetAddress.getByName("0.0.0.0"));
            this.ipServidor = obtenerIpPrivada();


            System.out.println(ipServidor.getHostAddress());


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
        while (true) {
            Socket skCliente = skServidorTCP.accept();
            System.out.println("Cliente conectado: " + skCliente.getInetAddress());
            skClientes.add(skCliente);

            // Hilo para manejar la conexión con este cliente
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
                for (Socket x : skClientes) {
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

    /**
     * Conectar a un cliente al servidor mediante una conexion UDP para envio de
     * paquete
     */
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
    
    public void añadirCLiente(Cliente cliente){
        clientes.add(cliente);
    }

    public boolean clienteExiste(Cliente cliente) {
        boolean existe = false;

        for (Cliente c : clientes) {
            if (c.equals(cliente)) {
                existe = true;
            }
        }
        return existe;
    }
    
    public InetAddress getIp(){
        return ipServidor;
    }
    
    private InetAddress obtenerIpPrivada() {
    try {
        java.util.Enumeration<java.net.NetworkInterface> interfaces = java.net.NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            java.net.NetworkInterface netInterface = interfaces.nextElement();
            java.util.Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress()) {
                    return addr;
                }
            }
        }
    } catch (Exception e) {
        System.err.println("No se pudo obtener IP local: " + e.getMessage());
    }
    // Fallback a localhost si no se encuentra ninguna IP
    try {
        return InetAddress.getLocalHost();
    } catch (Exception e) {
        return null;
    }
}


}
