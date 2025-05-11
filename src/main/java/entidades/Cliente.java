package entidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author angel
 */
public class Cliente implements Runnable{

    private String nombre;
    private String contraseña;
    private InetAddress direccionServidor;
    private InetAddress ip;
    private int puertoTCP;
    //private int puertoUDP;

    public Cliente(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    @Override
    public void run(){
        new Thread(() -> {
            try {
                gestorTCP();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
    
    public void asignarServidor(String direccionServidor, int puertoTCP) throws IOException {
        try{
            this.direccionServidor = InetAddress.getByName(direccionServidor);
            this.puertoTCP = puertoTCP;
        }catch(IOException ex){

        }
        //this.puertoUDP = puertoTCP + 1;
    }

    public void gestorTCP() throws IOException {
        if(this.direccionServidor == null){return;}
        try {
            Socket socket = new Socket(direccionServidor, puertoTCP);
            this.ip = socket.getLocalAddress();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(nombre + " se ha conectado al servidor. Escribe mensajes:");

            //Hilo para estar escuchando al servidor
            new Thread(() -> {
                try {
                    escucharServidor(in);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR AL RECIBIR MENSAJE", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }).start();

            // Hilo principal para enviar mensajes
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String mensaje = scanner.nextLine();
                mensaje = this.getNombre()+ ": " + mensaje;
                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }
                out.println(mensaje);
            }
            scanner.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Se cerró la conexión (escucha).");
        }
        System.out.println("Conexión cerrada.");

    }

    public void escucharServidor(BufferedReader in) throws IOException {
        String respuesta;
        while ((respuesta = in.readLine()) != null) {
            System.out.println(respuesta);
        }
    }

    public void gestorUDP() throws SocketException, IOException {
        DatagramSocket socketUDP = new DatagramSocket();

        String respuesta = nombre + " Escribiendo...";
        byte[] respuestaBytes = respuesta.getBytes();
        DatagramPacket paqueteEscribiendo = new DatagramPacket(respuestaBytes, respuestaBytes.length);
        socketUDP.send(paqueteEscribiendo);
        socketUDP.close();
    }

    //getters
    public String getNombre() {
        return nombre;
    }

    public String getContraeña() {
        return contraseña;
    }

    public InetAddress getIp() {
        return ip;
    }

    //setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContraeña(String contraeña) {
        this.contraseña = contraeña;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

}
