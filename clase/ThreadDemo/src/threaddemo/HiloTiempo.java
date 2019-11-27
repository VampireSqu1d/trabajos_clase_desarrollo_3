package threaddemo;

import javax.swing.*;

public class HiloTiempo extends Thread {
    
    String name;
    Thread t;
    JLabel tiempo;
    boolean suspender;
    boolean pausar;
    
    public HiloTiempo(String name, JLabel tiempo) {
        t = new Thread(this, name);
        this.tiempo = tiempo;
    }
    
    public void run() {
       
            int minutos = 0;
            int horas = 0;
        for(int segundos = 0; segundos <= 59; segundos++) {
            
            
            //tiempo.setText(Integer.toString(segundos));
            if(segundos == 59){
                segundos = 0;
                minutos++;
            }
            if(horas == 59){
                minutos = 0;
                horas++;
            }
            
            tiempo.setText(horas + ":" + minutos + ":" + segundos);

            
            try {
                sleep(1000);
                synchronized (this) {
                    while(suspender) {
                        wait();
                    }
                    if(pausar) {
                        break;
                    }
                }
            }catch (Exception e) {}
            
            
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
