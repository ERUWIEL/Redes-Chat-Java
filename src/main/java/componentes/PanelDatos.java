package componentes;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDatos extends JPanel {

    public PanelDatos() {
        super(null);
        JPanel pnlEntradas = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblNomServidor = new JLabel("Nombre Del Servidor");
        lblNomServidor.setForeground(Color.WHITE);
        PTextField txtNomServer = new PTextField();
        JLabel lblIp = new JLabel("Ingrese La IP");
        lblIp.setForeground(Color.WHITE);
        PTextField txtIp = new PTextField();
        JLabel lblPuerto = new JLabel("Ingrese El Puerto");
        lblPuerto.setForeground(Color.WHITE);
        PTextField txtPuerto = new PTextField();
        JPanel pnlCapacidad = new JPanel(new GridLayout(1,3));
        JLabel lblCapacidad = new JLabel("Capacidad");
        lblCapacidad.setForeground(Color.WHITE);
        PTextField txtCapacidad = new PTextField();
        PButton btnMas = new PButton("+");
        PButton btnMenos = new PButton("-");
        pnlCapacidad.add(txtCapacidad);
        pnlCapacidad.add(btnMas);
        pnlCapacidad.add(btnMenos);

        pnlEntradas.add(lblNomServidor);
        pnlEntradas.add(txtNomServer);
        pnlEntradas.add(lblCapacidad);
        pnlEntradas.add(pnlCapacidad);
        pnlEntradas.add(lblIp);
        pnlEntradas.add(txtIp);
        pnlEntradas.add(lblPuerto);
        pnlEntradas.add(txtPuerto);

        pnlEntradas.setBounds(150, 150, 850, 300);
        add(pnlEntradas);
    }
}
