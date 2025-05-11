package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * clase de utilidad que ahorrara codigo Panel Button, es un panel que actua
 * como boton PButton!
 */
public class PButton extends JPanel {

    /**
     * recibe la ruta del icono y opcionalmente del titulo del icono para mostrarse
     * 
     * @param rutaIcono
     * @param etiqueta
     */
    public PButton(String rutaIcono, String etiqueta) {
        // configuraciones de diseño
        super(new BorderLayout());
        setBackground(new Color(137, 0, 127));
        JLabel texto = new JLabel(etiqueta, JLabel.CENTER);
        texto.setFont(new Font("Oswald", Font.BOLD, 25));
        texto.setForeground(new Color(0, 0, 0));
        add(texto, BorderLayout.CENTER);

        // configuracion del icono
        ImageIcon icono = new ImageIcon(rutaIcono);
        Image iconoFormateado = icono.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel lblImagen = new JLabel(new ImageIcon(iconoFormateado));
        add(lblImagen, BorderLayout.NORTH);

        // metodo hover del boton
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setBackground(new Color(167, 11, 175));
                texto.setForeground(new Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(137, 0, 127));
                texto.setForeground(new Color(0, 0, 0));
            }
        });
    }

    public PButton(String etiqueta) {
        super();
        setBackground(new Color(23, 2, 99));
        JLabel texto = new JLabel(etiqueta, JLabel.CENTER);
        texto.setFont(new Font("Calibri", Font.BOLD, 18));
        texto.setForeground(new Color(255, 255, 255));
        add(texto, BorderLayout.CENTER);

        // metodo hover del boton
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setBackground(new Color(173, 216, 230));
                texto.setForeground(new Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(23, 2, 99));
                texto.setForeground(new Color(255, 255, 255));
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Define la forma redondeada
        Shape clip = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20);
        g2d.setClip(clip);
        // Dibuja el fondo
        g2d.setColor(new Color(137, 0, 127));
        g2d.fill(clip);
        super.paintComponent(g);
    }

}
