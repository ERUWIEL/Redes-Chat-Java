/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import componentes.PButton;
import componentes.PTextField;
import entidades.Cliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author xdlol
 */
public class PanelDatosCliente extends JPanel {

    private List<PTextField> datos = new LinkedList<>();
    private List<PButton> botones = new LinkedList<>();
    
    private PTextField txtNombre = new PTextField();
    private PTextField txtContraseña = new PTextField();
    private Cliente cliente;

    public PanelDatosCliente(String titulo) {
        super(null);
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

        JLabel lblNombre = new JLabel("Nombre: ");
        lblNombre.setFont(new Font("Calibri", Font.BOLD, 19));
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setBounds(200, 150, 100, 20);
        
        txtNombre.setBounds(250, 140, 250, 30);

        JLabel lblContraseña = new JLabel("Contraseña: ");
        lblContraseña.setFont(new Font("Calibri", Font.BOLD, 19));
        lblContraseña.setForeground(Color.WHITE);
        lblContraseña.setBounds(200, 200, 100, 20);
        
        txtContraseña.setCampoNumerico();
        txtContraseña.setBounds(290, 190, 210, 30);

        // boton cancelar
        PButton btnCancelar = new PButton("cancelar");
        btnCancelar.setBounds(200, 360, 100, 30);
        // boton aceptar
        PButton btnAceptar = new PButton("aceptar");
        btnAceptar.setBounds(400, 360, 100, 30);

        botones.add(btnCancelar);
        botones.add(btnAceptar);

        add(lblTitulo);
        add(lblNombre);
        add(txtNombre);
        add(lblContraseña);
        add(txtContraseña);
        add(btnCancelar);
        add(btnAceptar);
        add(pnlOptiones);
        
        botones.get(1).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(datos == null){
                    Cliente cliente = new Cliente(datos.get(0).toString(), datos.get(1).toString());
                }
            }
        });
    }
    
    public Cliente getCliente(){
        return this.cliente;
    }
    
                    
}
