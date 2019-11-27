package threaddemo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ThMoverImagen extends Thread {

    Thread t;
    JLabel carro;
    public boolean suspender;
    public boolean pausar;

    public ThMoverImagen(String name, JLabel carro){
        t = new Thread(this, name);
        this.carro = carro;
    }

    @Override
    public void run() {
        
        for (int i = 20; i < 345; i++) {
            carro.setBounds(i,carro.getY(), 24,24);
            try{
                sleep(20);

                if (i == 344){
                    JOptionPane.showMessageDialog(null, "Nice");
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

            carro.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent keyEvent) {

                }

                @Override
                public void keyPressed(KeyEvent keyEvent) {

                }

                @Override
                public void keyReleased(KeyEvent keyEvent) {
                    if (keyEvent.getKeyChar() == 'w' || keyEvent.getKeyChar() == 'W' || keyEvent.getKeyChar() == KeyEvent.VK_UP){
                        if (carro.getY() > 20)
                            carro.setLocation(carro.getX(), 20);
                    }
                    if (keyEvent.getKeyChar() == 's' || keyEvent.getKeyChar() == 'S' || keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                        if (carro.getY() < 40)
                            carro.setLocation(carro.getX(), 45);
                    }

                }
            });
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

}
