package componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PMenuCliente extends JPanel {
    private JPanel pnlPadre;
    private PButton btnUnirse;
    private PButton btnRegresar;


    public PMenuCliente(JPanel pnlPadre) {
        super(null);
        this.pnlPadre = pnlPadre;
        setBackground(new Color(84, 0, 81));

        Font font = new Font("Oswald", Font.PLAIN, 20);
        //label de nombre del servidor
        JLabel lblNomUsuario = new JLabel("Nombre De Usuario");
        lblNomUsuario.setForeground(Color.WHITE);
        lblNomUsuario.setFont(font);
        lblNomUsuario.setBounds(150, 50, 200, 30);
        //JTextField de nombre del servidor
        PTextField txtNomUsuario = new PTextField();
        txtNomUsuario.setBounds(350, 50, 230, 30);

        //label de ip
        JLabel lblIp = new JLabel("IP Del Servidor");
        lblIp.setForeground(Color.WHITE);
        lblIp.setFont(font);
        lblIp.setBounds(150, 100, 200, 30); //x, y, width, height
        //JTextField de ip
        PTextField txtIp = new PTextField();
        txtIp.setBounds(350, 100, 230, 30);

        //label de puerto
        JLabel lblPuerto = new JLabel("Puerto Del Servidor");
        lblPuerto.setForeground(Color.WHITE);
        lblPuerto.setFont(font);
        lblPuerto.setBounds(150, 150, 200, 30);
        //JTextField de puerto
        PTextField txtPuerto = new PTextField();
        txtPuerto.setCampoNumerico();
        txtPuerto.setBounds(350, 150, 230, 30);

        //boton de regresar
        btnRegresar = new PButton("REGRESAR",null,null);
        btnRegresar.setBounds(150, 250, 200, 30);
        btnUnirse = new PButton("UNIRSE", null, null);
        btnUnirse.setBounds(380, 250, 200, 30);

        //añadir los componentes al panel
        add(lblNomUsuario);
        add(txtNomUsuario);
        add(lblIp);
        add(txtIp);
        add(lblPuerto);
        add(txtPuerto);
        add(btnRegresar);
        add(btnUnirse);

        runBtnUnirse();
        runBtnRegresar();
    }

    private void runBtnUnirse() {
        btnUnirse.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // TODO add your handling code here:
                System.out.println("Botón Unirse clickeado");
            }
        });
    }
    
    private void runBtnRegresar(){
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setVisible(false);
                pnlPadre.setVisible(true);
            }
        });
    }
}