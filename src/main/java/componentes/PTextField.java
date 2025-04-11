package componentes;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class PTextField extends JTextField {

    public PTextField() {
        super();
        setFont(new Font("Calibri", Font.BOLD, 18));
        setForeground(new Color(255, 255, 255));
        setBackground(new Color(23, 2, 99));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));

        // agrega animaciones
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(173, 216, 230));
                setForeground(new Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(23, 2, 99));
                setForeground(new Color(255, 255, 255));
            }
        });
    }

    /**
     * Metodo que formatea el componente para solo acepta numeros como entrada
     */
    public void setCampoNumerico() {
        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str.matches("[0-9]*")) { 
                    super.insertString(offs, str, a);
                }
            }
        });
    }

    /**
     * Metodo que formatea el componente para solo acepta letras como entrada
     */
    public void setCampoLetra() {
        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*")) { 
                    super.insertString(offs, str, a);
                }
            }
        });
    }
}
