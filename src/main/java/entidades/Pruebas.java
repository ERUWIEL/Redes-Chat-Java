package entidades;

import java.util.Scanner;

/**
 *
 * @author angel
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // instancias
        Servidor sv = new Servidor();
        Thread hiloServer = new Thread(sv);
        Cliente cl = new Cliente();
        Thread hiloCliente = new Thread(cl);
        Scanner sc = new Scanner(System.in);
        // opciones
        System.out.println("QUE QUIERES SER?");
        System.out.println("1. Cliente");
        System.out.println("2. Servidor");
        int opt = sc.nextInt();
        
        // eleccion
        if (opt == 2) {
            hiloServer.start();
        } else {
            hiloCliente.start();
        }
    }

}
