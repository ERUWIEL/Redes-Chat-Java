
package entidades;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JTextArea;

import componentes.PButton;
import componentes.PTextField;

/**
 * clase que representa un socket
 *
 * @author erubiel
 */
public class Servidor {
    //variables globales
    private ServerSocket socket;
    private static HashMap<ObjectOutputStream, Encriptador> encriptadores = new HashMap<>();
    private static HashMap<String, InetAddress> clientes = new HashMap<>();
    private static LinkedList<String> historial = new LinkedList<>();

    // variables de instancia
    private String nombreServidor;
    private int capacidad;
    private InetAddress ip;
    private int puerto;
    private String nombreAdmin;

    // componentes de la interfaz
    private JTextArea chat;
    private PTextField texto;
    private PButton btnEnviar;

    public Servidor(String nombreServidor, String nombreAdmin, String ip, int puerto, int capacidad)
            throws IOException {
        this.nombreServidor = nombreServidor;
        this.nombreAdmin = nombreAdmin;
        this.ip = InetAddress.getByName(ip);
        this.puerto = puerto;
        this.capacidad = capacidad;
    }

    public void asignarComponentes(JTextArea chat, PTextField texto, PButton btnEnviar) {
        this.btnEnviar = btnEnviar;
        this.chat = chat;
        this.texto = texto;
    }

    //metodos de coneccion principales

    /**
     * inicia el servidor
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void iniciarServidor() throws IOException, ClassNotFoundException {
        socket = new ServerSocket(puerto, capacidad, ip);
        chat.append("Servidor " + nombreServidor + " iniciado\n");
        new Thread(() -> {
            try {
                escucharConexiones(socket);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();

        // Agregar el ActionListener al botón de enviar
        runBtnEnviar();
    }

    /**
     * escucha las conecciones entrantes
     * 
     * @param socket
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void escucharConexiones(ServerSocket socket) throws IOException, ClassNotFoundException {
        while (true) {
            Socket cliente = socket.accept();
            ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream()); //wrapper salida
            out.flush();
            ObjectInputStream in = new ObjectInputStream(cliente.getInputStream()); //wrapper ingreso
            

            Encriptador en = handshake(in, out); //determina una clave secreta de comunicacion
            System.out.println("handshake realizado");
            String nombreCliente = validarDatos(en,in,out); //determina si el cliente es valido
            System.out.println("Cliente valido");
            clientes.put(nombreCliente, cliente.getInetAddress()); //agrega el cliente a la lista de clientes
            new Thread(()-> manejaUsuario(en, in, nombreCliente)).start();; //asignar un hilo para el cliente
        }
    }

    /**
     * maneja la coneccion de un usuario
     * 
     * @param cliente
     */
    private void manejaUsuario(Encriptador encriptador, ObjectInputStream in, String nombreCliente) {
        try {
            while (true) {
                System.out.println("Esperando mensaje de " + nombreCliente);
                String mensaje = (String) in.readObject();  
                System.out.println("Mensaje recibido: " + mensaje);
                if (mensaje instanceof String) {
                    encriptador.setMensaje(mensaje);
                    encriptador.decifrar();
                    System.out.println("Mensaje desencriptado: " + encriptador.getMensaje());
                    enviarMensaje(nombreCliente,encriptador.getMensaje());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Conexión cerrada o error al leer del socket.");
            e.printStackTrace();
        }
    }


    //metodos de coneccion auxiliares

    /**
     * recibe los parametros de alice y manda los de bob
     * 
     * @param cliente
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Encriptador handshake(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        BigInteger[] aliceParam = (BigInteger[]) in.readObject();
        Encriptador encriptador = new Encriptador(aliceParam); //manda los parametros de bob
        out.writeObject(encriptador.getParam());
        out.flush();
        out.reset();
        return encriptador;
    }

    private String validarDatos(Encriptador encriptador, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        String mensaje = (String) in.readObject();// recibe el nombre del cliente de manera segura
        encriptador.setMensaje(mensaje);
        encriptador.decifrar();
        String nombreCliente = encriptador.getMensaje();
        
        //validacion de nombre (no nombres reptidos, no nombres de admin, no nombres de servidor)
        if(nombreCliente == nombreAdmin || nombreCliente =="SERVIDOR" || clientes.containsKey(nombreCliente)){
            throw new IOException("Nombre de usuario no valido");
        } else {
            encriptadores.put(out, encriptador);//agrega el cliente a la lista de clientes    
            encriptador.setMensaje(nombreServidor);// manda el nombre del servidor de manera segura una vez aceptado
            encriptador.cifrar();
            out.writeObject(encriptador.getMensaje());
            out.flush();
            out.reset();

            // manda el historial de mensajes al cliente
            for(String m : historial){
                encriptador.setMensaje(m);
                encriptador.cifrar();
                out.writeObject(encriptador.getMensaje());
                out.flush();
                out.reset();
            }
            enviarMensaje("SERVIDOR", "Nuevo Cliente Conectado: " + nombreCliente);
        }
        return nombreCliente;
    }

    /**
     * envia un mensaje al cliente
     * 
     * @param mensaje
     * @throws IOException
     */
    private void enviarMensaje(String emisor, String mensaje) throws IOException {
        String tiempo = String.format("%02d:%02d", Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE));
        final String msjFormateado = "[" +tiempo + "][" + emisor + "]: " + mensaje;

        encriptadores.forEach((out, encriptador)->{
            try {
                encriptador.setMensaje(msjFormateado);
                encriptador.cifrar();
                out.writeObject(encriptador.getMensaje());
                out.flush();
                out.reset();
            } catch (IOException e) {
                System.out.println("Error al enviar el mensaje a " + socket.getInetAddress());
                e.printStackTrace();
            }
        });
        chat.append(msjFormateado + "\n");
        historial.add(msjFormateado);
    }

    /**
     * envia un mensaje al cliente
     * 
     * @param mensaje
     * @throws IOException
     */
    private void runBtnEnviar() {
        btnEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    enviarMensaje(nombreAdmin, texto.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally{
                    texto.setText("");
                }
            }
        });
    }
}
