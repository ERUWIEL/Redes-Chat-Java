package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import componentes.PTextField;


public class PanelDatos extends JPanel {

    private List<PTextField> datos = new LinkedList<>();

    public PanelDatos(String titulo) {
        super(null);
        datos.clear();
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
        pnlOptiones.setBounds(120, 30, 500, 500);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 19));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(250, 80, 250, 20);

        JLabel lblIP = new JLabel("IP : ");
        lblIP.setFont(new Font("Calibri", Font.BOLD, 19));
        lblIP.setForeground(Color.WHITE);
        lblIP.setBounds(200, 150, 100, 20);

        PTextField txtIP = new PTextField();
        txtIP.setBounds(250, 140, 250, 30);

        JLabel lblPuerto = new JLabel("PUERTO : ");
        lblPuerto.setFont(new Font("Calibri", Font.BOLD, 19));
        lblPuerto.setForeground(Color.WHITE);
        lblPuerto.setBounds(200, 200, 100, 20);

        PTextField txtPuerto = new PTextField();
        txtPuerto.setCampoNumerico();
        txtPuerto.setBounds(290, 190, 210, 30);

        datos.add(txtIP);
        datos.add(txtPuerto);

        add(lblTitulo);
        add(lblIP);
        add(txtIP);
        add(lblPuerto);
        add(txtPuerto);
        add(pnlOptiones);
    }

    public String getDatoIp(){
        return datos.get(0).getText();
    }

    public int getDatoPuerto(){
        return Integer.parseInt(datos.get(1).getText());
    }
}
