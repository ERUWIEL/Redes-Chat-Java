package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
 
/**
 * clase de utilidad que ahorrara codigo Panel Button, es un panel que actua como boton PButton!
 */
public class PButton extends JPanel {

    private Color outColorBoton = new Color(23, 2, 99);
    private Color inColorBoton = new Color(173, 216, 230);

    public PButton(String etiqueta){
        super();
        setBackground(outColorBoton);
        JLabel texto = new JLabel(etiqueta, JLabel.CENTER);
        texto.setFont(new Font("Calibri", Font.BOLD, 18));
        texto.setForeground(Color.WHITE);
        add(texto, BorderLayout.CENTER);

        // metodo hover del boton
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setBackground(inColorBoton);
                texto.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(outColorBoton);
                texto.setForeground(Color.WHITE);
            }
        });
    }
}
