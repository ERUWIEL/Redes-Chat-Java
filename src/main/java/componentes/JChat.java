package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JChat extends JFrame { 
    private String nombreChat;
    private String nombreAdmin;


    private JTextArea txtArea;
    private PButton btnEnviar;
    private PTextField txtMensaje;
    private JLabel lblAdmin;
    private JLabel lblNombreChat;

    public JChat(String nombreChat, String nombreAdmin) {
        this.nombreChat = nombreChat;
        this.nombreAdmin = nombreAdmin;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setTitle(nombreChat);
        setIconImage(new ImageIcon("src/main/resources/chat-icono.png").getImage());
        setLayout(new BorderLayout());
        agregarPnlNorte();
        agregarPnlCentro();
        agregarPnlEste();
        agregarPnlSur();
    }

    private void agregarPnlNorte() {
        // configuracion del panel norte
        JPanel pnlNorte = new JPanel();
        pnlNorte.setLayout(null);
        pnlNorte.setBackground(new Color(33, 1, 46));
        pnlNorte.setPreferredSize(new Dimension(getWidth(), 50));

        lblNombreChat = new JLabel(nombreChat);
        lblNombreChat.setForeground(Color.WHITE);
        lblNombreChat.setFont(new Font("Oswald", Font.BOLD, 20));
        lblNombreChat.setBounds(40, 10, 200, 30); // x, y, width, height
        pnlNorte.add(lblNombreChat);

        JPanel pnlNomAdmin = new JPanel();
        pnlNomAdmin.setBackground(new Color(84,0,81));
        JLabel lblNomAdmin = new JLabel("ADMIN");
        lblNomAdmin.setForeground(Color.WHITE);
        lblNomAdmin.setFont(new Font("Oswald", Font.BOLD, 16));
        pnlNomAdmin.add(lblNomAdmin);
        pnlNomAdmin.setBounds(390, 10, 70, 30); // x, y, width, height
        pnlNorte.add(pnlNomAdmin);

        JPanel pnlAdmin = new JPanel();
        pnlAdmin.setBackground(new Color(137,0,127));
        lblAdmin = new JLabel(nombreAdmin);
        lblAdmin.setForeground(Color.WHITE);
        lblAdmin.setFont(new Font("Oswald", Font.BOLD, 16));
        pnlAdmin.add(lblAdmin);
        pnlAdmin.setBounds(460, 10, 130, 30); // x, y, width, height        
        pnlNorte.add(pnlAdmin);

        JLabel lblUsuarios = new JLabel("USUARIOS");
        lblUsuarios.setForeground(Color.WHITE);
        lblUsuarios.setFont(new Font("Oswald", Font.BOLD, 20));
        lblUsuarios.setBounds(655, 10, 200, 30); // x, y, width, height
        pnlNorte.add(lblUsuarios);

        //agregaciones de paneles
        add(pnlNorte, BorderLayout.NORTH);
    }

    private void agregarPnlCentro() {
        // configuracion del panel centro
        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(null);
        pnlCentro.setBackground(new Color(84, 0, 81));

        txtArea = new JTextArea();
        txtArea.setBackground(new Color(46,2,48));
        txtArea.setEditable(false);
        txtArea.setFocusable(false);
        txtArea.setFont(new Font("Oswald", Font.PLAIN, 16));
        txtArea.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(txtArea);
        scrollPane.setBounds(40, 0, 550, 430); // x, y, width, height
        
        pnlCentro.add(scrollPane);        
        add(pnlCentro, BorderLayout.CENTER);
    }

    private void agregarPnlEste() {
        // configuracion del panel oeste
        JPanel pnlOeste = new JPanel();
        pnlOeste.setBackground(new Color(33, 1, 46));
        pnlOeste.setPreferredSize(new Dimension(150, getHeight()));
        add(pnlOeste, BorderLayout.EAST);
    }

    private void agregarPnlSur(){
        JPanel pnlSur = new JPanel();
        pnlSur.setLayout(null);
        pnlSur.setBackground(new Color(33, 1, 46));
        pnlSur.setPreferredSize(new Dimension(getWidth(), 80));

        txtMensaje = new PTextField();
        txtMensaje.setBounds(40, 20, 450, 40);
        btnEnviar = new PButton("ENVIAR",Color.WHITE,new Color(6,79,146));
        btnEnviar.setBounds(490, 22, 100, 36);

        pnlSur.add(txtMensaje);
        pnlSur.add(btnEnviar);
        add(pnlSur, BorderLayout.SOUTH);
    }

    public PTextField getTxtMensaje() {
        return txtMensaje;
    }
    public PButton getBtnEnviar() {
        return btnEnviar;
    }
    public JTextArea getTxtArea() {
        return txtArea;
    }
    public JLabel getLblServer() {
        return lblNombreChat;
    }
    public JLabel getLblAdmin() {
        return lblAdmin;
    }
    public void setNewTitle(String title) {
        setTitle(title);
    }
}
