package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import componentes.PButton;
import entidades.Cliente;
import entidades.Servidor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;

/**
 * clase que representa una ventana principal de un chat
 *
 * @author erubiel
 */
public class Pruebas extends JFrame {

    private String operacion;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int puerto =30002;
        InetAddress direccionServidor = InetAddress.getByName("192.168.56.1");
        Cliente clienteDummy = new Cliente("Admin", "1234");
        Servidor servidor = new Servidor(puerto);
        clienteDummy.asignarServidor(direccionServidor, puerto);
        
        System.out.println("Servidor corriendo en IP: " + servidor.getIp().getHostAddress());

    }

    /**
     * Metodo constructor de la ventana
     */
    public Pruebas() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setTitle("TCPvChat");
        setLayout(new BorderLayout());
        initComponents();
    }

    /**
     * Metodo que inicializa los componentes visuales de la ventana
     */
    private void initComponents() {
        JPanel pnlCentral = new JPanel();
        pnlCentral.setLayout(null); // AbsoluteLayout

        // panel redondo personalizado usando clases internas anonimas
        JPanel pnlOptiones = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Define la forma redondeada
                Shape clip = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setClip(clip);
                // Dibuja el fondo
                g2d.setColor(new Color(0, 1, 12));
                g2d.fill(clip);
                super.paintComponent(g);
            }
        };
        pnlOptiones.setOpaque(false);
        pnlOptiones.setBounds(230, 100, 300, 300);
        // Label del titulo
        JLabel lblTitulo = new JLabel("CHAT TCP/IP");
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 19));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(325, 120, 100, 20);
        // boton cliente
        PButton btnServidor = new PButton("Crear Servidor");
        btnServidor.setBounds(230, 180, 300, 30);
        // boton servidor
        PButton btnCliente = new PButton("Unirse a Servidor");
        btnCliente.setBounds(230, 240, 300, 30);

        // agregaciones al panel principal que implementa un AbsoluteLayout
        pnlCentral.add(lblTitulo);
        pnlCentral.add(btnServidor);
        pnlCentral.add(btnCliente);
        pnlCentral.add(pnlOptiones);
        add(pnlCentral, BorderLayout.CENTER);

        // panel de recoleccion de datos de creacion
        PanelDatos pnlDatos = new PanelDatos("Informacion Del Servidor");

        // MouseListener del boton cliente
        btnCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operacion = "cliente";
                remove(pnlCentral);
                add(pnlDatos, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        // MouseListener del boton servidor
        btnServidor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operacion = "servidor";
                remove(pnlCentral);
                add(pnlDatos, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        pnlDatos.getBotonCancelar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                operacion = null;
                pnlDatos.borrarDatos();
                remove(pnlDatos);
                add(pnlCentral, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        pnlDatos.getBotonAceptar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });

    }
}
