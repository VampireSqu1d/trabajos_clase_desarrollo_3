package threaddemo;

import javax.swing.*;
import java.awt.geom.Area;

public class ThRock extends Thread {
    Thread t;
    ThMoverImagen thMoverImagen;
    JLabel roca, carro;
    public boolean suspender;
    public boolean pausar;
    int anInt;

    public ThRock(String name, JLabel roca, JLabel carro, ThMoverImagen thMoverImagen, int anInt){
        t = new Thread(this, name);
        this.roca = roca;
        this.carro = carro;
        this.thMoverImagen = thMoverImagen;
        this.anInt = anInt;
    }

    @Override
    public void run() {

        for (int i = 350; i > 15 ; i--) {
            roca.setBounds(i, roca.getY(), 24, 24);
            try {
                sleep(anInt);

                if(interseccion(roca,carro) ) {
                    thMoverImagen.stopHilo();
                    JOptionPane.showMessageDialog(null,"Big Off");
                    break;
                }

                synchronized (this) {
                    while(suspender) {
                        wait();
                    }
                    if(pausar) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    synchronized void suspenderHilo() {
        suspender = true;
    }

    synchronized void reanudarHilo() {
        suspender = false;
        notify();
    }

    public synchronized void stopHilo() {
        suspender = false;
        pausar = true;
        notify();
    }

    public boolean interseccion(JLabel rocka, JLabel carro) {
        Area a = new Area(rocka.getBounds());
        Area b = new Area(carro.getBounds());

        return a.intersects(b.getBounds2D());
    }

}
