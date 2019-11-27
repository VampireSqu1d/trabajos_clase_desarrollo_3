package threaddemo;

import javax.swing.*;

public class TextoThread extends Thread {

    public Thread thread;
    public JLabel label1;
    public  JLabel segundoLabel;
    public boolean suspender;
    public boolean pausar;

    public TextoThread(String name, JLabel label, String[] arreglo, JLabel ventana){
        thread = new Thread(this, name);
        this.label1 = label;
        this.segundoLabel = new JLabel();
    }


    public void run(){
        String texto = label1.getText();

        int largo = texto.length() - 1;
        JLabel inicio = new JLabel(texto.substring(0,1));
        for (int i = 20; i <= 360; i = i + 30) {
            label1.setBounds(i, 50, 90, 20);
            try{
                sleep(200);
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

            if (i >= 180 && largo > 0){
                label1.setText(texto.substring(0, largo));
                largo = largo -1;
            }

            if (i >= 300){
                i = 20;
                label1.setText(texto);
                largo = texto.length();
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

    synchronized void stopHilo() {
        suspender = false;
        pausar = true;
        notify();
    }

}
