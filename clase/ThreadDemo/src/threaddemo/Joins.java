package threaddemo;

public class Joins extends Thread {
    
    Thread t;
    int time;
    
    public Joins(String name, int time) {
        t = new Thread(this, name);
        this.time = time;
    }
    
    public void run() {
        for(int i=0; i<5; i++) {
            try { sleep(time); } catch(Exception e) {}
            System.out.println("Thread : " + t);
        }
        System.out.println("Termina thread : " + t);
    } //end run
    
}
