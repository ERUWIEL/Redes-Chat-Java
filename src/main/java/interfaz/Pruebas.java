package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

//import entidades.Cliente;
//import entidades.Servidor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.util.Scanner;
import componentes.PButton;
import componentes.PanelDatos;

import java.io.IOException;


/**
 * clase que representa una ventana principal de un chat
 * 
 * @author erubiel
 */
public class Pruebas extends JFrame {
    //private String operacion;
    private JPanel pnlContenido;
    private PButton btnCrear;
    private PButton btnUnirse;
    private JPanel pnlCentro;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
    }

    /**
     * Metodo constructor de la ventana
     */
    public Pruebas() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setTitle("TCPvChat");
        setIconImage(new ImageIcon("src/main/resources/chat-icono.png").getImage());
        setLayout(new BorderLayout());
        initComponents();
    }

    /**
     * Metodo que inicializa los componentes visuales de la ventana
     */
    private void initComponents() {
        // panel norte
        JPanel pnlNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        pnlNorte.setBackground(new Color(33, 1, 46));
        JLabel titulo = new JLabel("TCPvCHAT");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Oswald", Font.PLAIN, 45));
        pnlNorte.add(titulo);
        pnlNorte.setPreferredSize(new Dimension(getWidth(), 90));
        add(pnlNorte, BorderLayout.NORTH);

        // panel centro
        pnlCentro = new JPanel();
        pnlCentro.setBackground(new Color(84, 0, 81));

        pnlContenido = new JPanel();
        pnlContenido.setLayout(null);
        
        // botones
        btnCrear = new PButton("src/main/resources/server-icono.png", "CREAR");
        btnUnirse = new PButton("src/main/resources/unirse-icono.png", "UNIRSE");
        btnCrear.setBounds(150, 150, 350, 350);
        btnUnirse.setBounds(700, 150, 350, 350);
        pnlContenido.add(btnCrear);
        pnlContenido.add(btnUnirse);
        pnlCentro.add(pnlContenido);

        add(pnlCentro, BorderLayout.CENTER);
        runBtnCrear();
        runBtnUnirse();
    }

    private void runBtnCrear() {
        btnCrear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(pnlContenido);
                pnlContenido = new PanelDatos();
                pnlContenido.setOpaque(true);
                add(pnlContenido, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
    }

    private void runBtnUnirse() {
        btnUnirse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }
}
