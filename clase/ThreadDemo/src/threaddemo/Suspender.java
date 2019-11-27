package threaddemo;

public class Suspender extends Thread {
    
    Thread t;
    int time;
    boolean suspender;
    boolean pausar;
    
    public Suspender(String name, int time) {
        t = new Thread(this, name);
        this.time = time;
    }
    
    public void run() {
        System.out.println("Iniciando : " + t);
        for(int i=1; i<=100; i++) {
            System.out.print(i + " ");
            if(i%10 == 0) {
                System.out.println();
            }
            try { sleep(time); } catch(Exception e) {}
            
            try {
                synchronized (this) {
                    while(suspender) {
                        wait();
                    }
                    if(pausar) {
                        break;
                    }
                }
            } catch(Exception e) {
            
            }
            
            
        } //end for
        System.out.println("Termina el thread : " + t);
    } //end run
    
    synchronized void suspenderHilo() {
        suspender = true;
    }
    
    synchronized void reanudarHilo() {
        suspender = false;
        notify();
    }
    
    synchronized void stopHilo() {
        pausar = true;
        suspender = false;
        notify();
    }
    
}
