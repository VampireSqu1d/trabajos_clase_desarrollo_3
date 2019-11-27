package threaddemo;

import javax.swing.*;

public class VentanaThExtends extends Thread {
    
    Thread t;
    JButton boton;
    
    public VentanaThExtends(JButton boton) {
        this.boton = boton;
    }
    
    public void run() {
        boton.setEnabled(false);
        for(int i=0; i<=10; i++) {
            System.out.println(i);
            boton.setText(Integer.toString(i));
            try {
                sleep(1000);
            } catch(Exception e) {
                System.out.println("Error, thread interrumpido");
            }
        }
        boton.setEnabled(true);
        boton.setText("Inicio");
    }
    
}
