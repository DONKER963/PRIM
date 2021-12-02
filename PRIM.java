package prim;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;



/**
 *
 * @author cuent
 */
public class PRIM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int nodoINI;
        Grafo g = new Grafo();
        Scanner leer = new Scanner(System.in);
        try {
            g.LeeGrafo("C:\\Users\\cuent\\Documents\\Universidad\\Estructuras de Datos\\d\\grafo.txt");
        } catch (IOException ex) {
            Logger.getLogger(PRIM.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.EscribeM();
        
        System.out.println("Nodo por el cual iniciar ");
        nodoINI = leer.nextInt();
        //
        nodo ini = new nodo(nodoINI, 0);
        g.ayacentes(ini);
        g.EscribeAEM();
        g.grado();
        
    }
    
}
