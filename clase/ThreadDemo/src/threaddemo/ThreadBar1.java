package threaddemo;

import javax.swing.*;

public class ThreadBar1 extends Thread {
    Thread t;
    String name;
    int time;
    JProgressBar b;
     boolean suspender;
    boolean pausar;
    
    public ThreadBar1(String name, int time, JProgressBar b){
        t = new Thread(this, name);
        this.name = name;
        this.time = time;
        this.b = b;
    }
    
    public void run() {
        for (int i = 0; i <= 100; i++) {
            b.setValue(i);
             try {
                sleep(time);
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
