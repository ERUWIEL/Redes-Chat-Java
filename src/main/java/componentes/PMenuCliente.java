package componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PMenuCliente extends JPanel {

    public PMenuCliente() {
        super(null);
        setBackground(new Color(84, 0, 81));

        Font font = new Font("Oswald", Font.PLAIN, 20);
        //label de nombre del servidor
        JLabel lblNomServidor = new JLabel("Nombre Del Servidor");
        lblNomServidor.setForeground(Color.WHITE);
        lblNomServidor.setFont(font);
        lblNomServidor.setBounds(150, 50, 200, 30);
        //JTextField de nombre del servidor
        PTextField txtNomServer = new PTextField();
        txtNomServer.setBounds(350, 50, 230, 30);

        //label de capacidad
        JLabel lblCapacidad = new JLabel("Capacidad");
        lblCapacidad.setForeground(Color.WHITE);
        lblCapacidad.setFont(font);
        lblCapacidad.setBounds(150, 100, 200, 30);
        //JTextField de capacidad
        PTextField txtCapacidad = new PTextField();
        txtCapacidad.setEditable(false);
        txtCapacidad.setFocusable(false);
        txtCapacidad.setBounds(350, 100, 130, 30);
        //label de maximo
        PButton btnMas = new PButton("+");
        btnMas.setBounds(480, 100, 50, 30);
        //label de minimo
        PButton btnMenos = new PButton("-");
        btnMenos.setBounds(530, 100, 50, 30);

        //label de ip
        JLabel lblIp = new JLabel("IP Del Servidor");
        lblIp.setForeground(Color.WHITE);
        lblIp.setFont(font);
        lblIp.setBounds(150, 150, 200, 30); //x, y, width, height
        //JTextField de ip
        PTextField txtIp = new PTextField();
        txtIp.setBounds(350, 150, 230, 30);

        //label de puerto
        JLabel lblPuerto = new JLabel("Puerto Del Servidor");
        lblPuerto.setForeground(Color.WHITE);
        lblPuerto.setFont(font);
        lblPuerto.setBounds(150, 200, 200, 30);
        //JTextField de puerto
        PTextField txtPuerto = new PTextField();
        txtPuerto.setCampoNumerico();
        txtPuerto.setBounds(350, 200, 230, 30);
        //añadir los componentes al panel
        add(lblNomServidor);
        add(txtNomServer);
        add(lblCapacidad);
        add(txtCapacidad);
        add(btnMas);
        add(btnMenos);
        add(lblIp);
        add(txtIp);
        add(lblPuerto);
        add(txtPuerto);
    }


}